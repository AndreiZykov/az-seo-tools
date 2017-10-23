package com.zykov.andrii.azseotools.utils;

import android.util.Log;

/**
 * Created by andrii on 10/21/17.
 */

public class Logger {
    private static final boolean DEBUG = true;
    public static void print(String tag, String log){
        if(DEBUG)
        Log.i(tag,log);
    }
}
