package com.zykov.andrii.azseotools.fragment.seofragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zykov.andrii.azseotools.R;
import com.zykov.andrii.azseotools.adapters.SeoRecycleViewAdapter;
import com.zykov.andrii.azseotools.objects.SeoObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andrii on 10/21/17.
 */

public class SeoFragment extends Fragment implements ISeoFragment {

    public static final String TAG = SeoFragment.class.getCanonicalName();

    public static final String URL_STRING_KEY = "URL_STRING_KEY";

    private final Handler handler = new Handler();

    @BindView(R.id.seo_fragment_recycle_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar_wrapper)
    RelativeLayout progressBar;

    @BindView(R.id.something_went_wrong_layout)
    RelativeLayout somethingWentWrongLayout;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ISeoFragmentPresenter presenter;

    public static SeoFragment getInstance(Bundle args) {
        SeoFragment fr = new SeoFragment();
        fr.setArguments(args);
        return fr;
    }


    private String url;

    @OnClick({R.id.something_went_wrong_layout})
    public void onClick(View v) {
        if (v.getId() == R.id.something_went_wrong_layout) {
            presenter.getSeoDataAsync(url);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.seo_fragment, container, false);
        ButterKnife.bind(this, rootView);
        recyclerView.setHasFixedSize(true);
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        presenter = new SeoFragmentPresenter(this);
        url = getArguments().getString(URL_STRING_KEY);
        presenter.getSeoDataAsync(url);
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public void showProgressBar() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                somethingWentWrongLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void showSeoData(List<SeoObject> seoElements) {
        if (seoElements != null && seoElements.size() > 0) {
            adapter = new SeoRecycleViewAdapter(seoElements);
            recyclerView.setAdapter(adapter);
            somethingWentWrongLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            somethingWentWrongLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideProgressBar() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void handleError(String error) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                somethingWentWrongLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public Context getCurrentContext() {
        return getActivity();
    }

    public interface ISeoFragmentPresenter {
        void getSeoDataAsync(String s);
    }

}
