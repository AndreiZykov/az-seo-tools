package com.zykov.andrii.azseotools.dagger;

import com.zykov.andrii.azseotools.fragment.searchfragment.SearchFragment;
import com.zykov.andrii.azseotools.fragment.searchfragment.SearchFragmentPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrii on 10/25/17.
 */

@Module
public class SearchFragmentPresenterModule {

    @Provides
    @Singleton
    SearchFragment.ISearchFragmentPresenter provideSearchFragmentPresenter(){
        return new SearchFragmentPresenter();
    }

}
