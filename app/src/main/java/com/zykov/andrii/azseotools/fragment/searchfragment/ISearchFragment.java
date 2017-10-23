package com.zykov.andrii.azseotools.fragment.searchfragment;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by andrii on 10/21/17.
 */

public interface ISearchFragment {
    void openSeoFragment(Bundle args);
    Context getCurrentContext();
    void updateData();
    void showAddURLDialog(String url);
}
