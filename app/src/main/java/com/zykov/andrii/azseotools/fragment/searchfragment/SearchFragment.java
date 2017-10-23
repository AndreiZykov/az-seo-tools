package com.zykov.andrii.azseotools.fragment.searchfragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zykov.andrii.azseotools.R;
import com.zykov.andrii.azseotools.dialogfragments.RemoveUrlDialogFragment;
import com.zykov.andrii.azseotools.dialogfragments.UrlDialogFragment;
import com.zykov.andrii.azseotools.fragment.seofragment.SeoFragment;
import com.zykov.andrii.azseotools.utils.database.DbProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andrii on 10/21/17.
 */

public class SearchFragment extends Fragment implements ISearchFragment {


    public static final String TAG = SearchFragment.class.getCanonicalName();

    public static SearchFragment getInstance() {
        return new SearchFragment();
    }

    private List<String> urlData;

    private ArrayAdapter<String> adapter;

    public void removeUrlDialogBtnClicked(String url) {
        presenter.removeURL(url);
    }

    public void addUrl(String url) {
        presenter.addUrl(url);
    }

    public void editUrlButtonClicked(String url) {
        showAddURLDialog(url);
    }

    public interface ISearchFragmentPresenter {
        void addUrl(String url);
        void removeURL(String url);
        void showURLSEOResults(String urlByPosition);
    }

    private ISearchFragmentPresenter presenter;

    @BindView(R.id.search_fragment_fab)
    FloatingActionButton fab;

    @BindView(R.id.url_list_search_fragment)
    ListView urlListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, rootView);
        urlData = new ArrayList<String>();
        updateData();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, urlData);
        urlListView.setAdapter(adapter);
        urlListView.setOnItemLongClickListener(onItemLongClickListener);
        urlListView.setOnItemClickListener(onItemClickListener);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        presenter = new SearchFragmentPresenter(this);
        return rootView;
    }

    @OnClick({R.id.search_fragment_fab})
    public void OnClick(View v) {
        if (v.getId() == R.id.search_fragment_fab) {
            showAddURLDialog(null);
        }
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
            String url = getURLByPosition(pos);
            presenter.showURLSEOResults(url);
        }
    };

    private void handleError(String message) {
        new AlertDialog.Builder(getActivity()).setTitle("Error").setMessage(message).show();
    }

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
            showRemoveUrlDialog(urlData.get(position));
            return true;
        }
    };

    private void showRemoveUrlDialog(final String url) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(UrlDialogFragment.TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        RemoveUrlDialogFragment dfr = RemoveUrlDialogFragment.newInstance(url);
        dfr.show(getActivity().getSupportFragmentManager(), RemoveUrlDialogFragment.TAG);
    }

    @Override
    public void showAddURLDialog(String url) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(UrlDialogFragment.TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        UrlDialogFragment dfr = UrlDialogFragment.newInstance(url);
        dfr.show(getActivity().getSupportFragmentManager(), UrlDialogFragment.TAG);
    }

    private String getURLByPosition(int position) {
        return urlData.get(position);
    }

    @Override
    public void openSeoFragment(Bundle args) {
        SeoFragment rf = SeoFragment.getInstance(args);
        FragmentTransaction tr = getActivity().getSupportFragmentManager().beginTransaction();
        tr.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        tr.replace(R.id.frame_layout_main_activity, rf, SeoFragment.TAG);
        tr.addToBackStack(SeoFragment.TAG);
        tr.commit();
    }

    @Override
    public Context getCurrentContext() {
        return getActivity();
    }

    @Override
    public void updateData() {
        DbProvider db = new DbProvider(getActivity());
        urlData.clear();
        urlData.addAll(db.getAllURLObjs());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}
