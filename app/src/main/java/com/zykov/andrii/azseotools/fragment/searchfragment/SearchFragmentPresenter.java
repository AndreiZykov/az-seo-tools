package com.zykov.andrii.azseotools.fragment.searchfragment;

import android.os.Bundle;
import android.util.Patterns;

import com.zykov.andrii.azseotools.fragment.seofragment.SeoFragment;
import com.zykov.andrii.azseotools.utils.database.DbProvider;

/**
 * Created by andrii on 10/21/17.
 */

public class SearchFragmentPresenter implements SearchFragment.ISearchFragmentPresenter {

    private ISearchFragment fragment;

    public SearchFragmentPresenter() {
    }

    @Override
    public void bindView(SearchFragment searchFragment) {
        this.fragment = searchFragment;
    }

    @Override
    public void addUrl(String url) {
        if(validateUrl(url)) {
            DbProvider db = new DbProvider(fragment.getCurrentContext());
            url = prepareUrl(url);
            db.saveUrl(url);
            fragment.updateData();
        } else {
            fragment.showAddURLDialog(url);
        }
    }

    private String prepareUrl(String url) {
        return url;
    }



    @Override
    public void removeURL(String url) {
        DbProvider db = new DbProvider(fragment.getCurrentContext());
        db.deleteUrl(url);
        fragment.updateData();
    }

    @Override
    public void showURLSEOResults(String url) {
        Bundle args = new Bundle();
        args.putString(SeoFragment.URL_STRING_KEY, url);
        fragment.openSeoFragment(args);
    }

    public boolean validateUrl(String url) {
        return Patterns.WEB_URL.matcher(url).matches();
    }

}
