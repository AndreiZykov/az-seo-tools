package com.zykov.andrii.azseotools.fragment.seofragment;

import android.content.Context;

import com.zykov.andrii.azseotools.adapters.SeoRecycleViewAdapter;
import com.zykov.andrii.azseotools.objects.SeoObject;

import java.util.List;

/**
 * Created by andrii on 10/21/17.
 */

public interface ISeoFragment {
    void showProgressBar();
    void showSeoData(List<SeoObject> seoElements);
    void hideProgressBar();
    void handleError(String error);
    Context getCurrentContext();
}
