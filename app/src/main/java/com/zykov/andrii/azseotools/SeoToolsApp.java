package com.zykov.andrii.azseotools;

import android.app.Application;

import com.zykov.andrii.azseotools.dagger.AppComponent;
import com.zykov.andrii.azseotools.dagger.AppModule;
import com.zykov.andrii.azseotools.dagger.DaggerAppComponent;

/**
 * Created by andrii on 10/25/17.
 */

public class SeoToolsApp extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(SeoToolsApp application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }

}
