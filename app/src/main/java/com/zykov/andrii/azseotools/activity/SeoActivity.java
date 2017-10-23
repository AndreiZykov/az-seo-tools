package com.zykov.andrii.azseotools.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.zykov.andrii.azseotools.R;
import com.zykov.andrii.azseotools.dialogfragments.RemoveUrlDialogFragment;
import com.zykov.andrii.azseotools.dialogfragments.UrlDialogFragment;
import com.zykov.andrii.azseotools.fragment.searchfragment.SearchFragment;
import com.zykov.andrii.azseotools.fragment.seofragment.SeoFragment;

public class SeoActivity extends AppCompatActivity implements UrlDialogFragment.UrlDialogFragmentListener, RemoveUrlDialogFragment.RemoveUrlDialogFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("AZ SEO Tools");
        SearchFragment searchFragment = SearchFragment.getInstance();
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_layout_main_activity, searchFragment, SearchFragment.TAG);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                SeoFragment fr = (SeoFragment) getSupportFragmentManager().findFragmentByTag(SeoFragment.TAG);
                if (fr != null && fr.isVisible()) {
                    return fr.onOptionsItemSelected(item);
                } else {
                    onBackPressed();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void removeUrl(String url) {
        SearchFragment fr = (SearchFragment) getSupportFragmentManager().findFragmentByTag(SearchFragment.TAG);
        if (fr != null && fr.isVisible()) {
            fr.removeUrlDialogBtnClicked(url);
        }
    }

    @Override
    public void addUrl(String url) {
        SearchFragment fr = (SearchFragment) getSupportFragmentManager().findFragmentByTag(SearchFragment.TAG);
        if (fr != null && fr.isVisible()) {
            fr.addUrl(url);
        }
    }
}
