package com.zykov.andrii.azseotools.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zykov.andrii.azseotools.utils.database.dbobjects.UrlObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrii on 10/21/17.
 */

public class DbProvider {

    public static final String TAG = DbProvider.class.getCanonicalName();

    private Context context = null;

    protected Context getContext() {
        return context;
    }

    public DbProvider(Context context) {
        this.context = context;
    }

    private SQLiteDatabase database = null;

    public void open() {
        database = AppDb.getDatabase(getContext());
    }

    public void close() {
        AppDb.releaseDatabase();
        //database = null;
    }

    public void saveUrl(String url) {
        try {
            open();
            if (database != null) {
                ContentValues cv = new ContentValues();
                Cursor cursor = database.query(AppDb.URLTable.TABLE_NAME,
                        AppDb.URLTable.TABLE_COLUMNS,
                        AppDb.URLTable.URL + " = ? ",
                        new String[]{String.valueOf(url)},
                        null,
                        null,
                        AppDb.URLTable.ID + " desc");

                if (cursor.getCount() == 0) {
                    cv.put(AppDb.URLTable.URL, url);
                    database.insert(AppDb.URLTable.TABLE_NAME, null, cv);
                }
            }
        } finally {
            close();
        }
    }

    public List<String> getAllURLObjs() {
        List<String> res = new ArrayList<>();
        try {
            open();
            if (database != null) {
                // We will select all trips where Start and Stop time stamps are exist
                Cursor cursor = database.query(
                        AppDb.URLTable.TABLE_NAME,
                        AppDb.URLTable.TABLE_COLUMNS,
                        null,
                        null,
                        null,
                        null,
                        null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        res.add(cursor2UrlObj(cursor).getUrl());
                        cursor.moveToNext();
                    }
                }
            }
        } finally {
            close();
        }

        return res;
    }

    private UrlObject cursor2UrlObj(Cursor cursor) {
        UrlObject obj = new UrlObject();
        if (!cursor.isNull(cursor.getColumnIndex(AppDb.URLTable.ID)))
            obj.setId(cursor.getInt(cursor.getColumnIndex(AppDb.URLTable.ID)));
        if (!cursor.isNull(cursor.getColumnIndex(AppDb.URLTable.URL)))
            obj.setUrl(cursor.getString(cursor.getColumnIndex(AppDb.URLTable.URL)));
        return obj;
    }

    public void deleteUrl(String url) {
        try {
            open();
            if (database != null) {
                 database.delete(AppDb.URLTable.TABLE_NAME, AppDb.URLTable.URL + " = ? ", new String[]{url});
            }
        } finally {
            close();
        }
    }

}
