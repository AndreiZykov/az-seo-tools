package com.zykov.andrii.azseotools.dagger;

import com.zykov.andrii.azseotools.fragment.searchfragment.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrii on 10/25/17.
 */

@Singleton
@Component(modules = {AppModule.class, SearchFragmentPresenterModule.class})
public interface AppComponent {
    void inject(SearchFragment target);
}


