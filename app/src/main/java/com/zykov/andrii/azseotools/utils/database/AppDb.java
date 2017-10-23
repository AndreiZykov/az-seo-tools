package com.zykov.andrii.azseotools.utils.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zykov.andrii.azseotools.utils.Logger;

/**
 * Created by andrii on 10/21/17.
 */

public class AppDb extends SQLiteOpenHelper {

    private Context context;

    protected Context getContext() {
        return context;
    }

    private static final String FILE_NAME = "seotools.db";
    private static final int DB_VERSION = 1;
    private final String TAG = AppDb.class.getCanonicalName();

    private AppDb(Context context) {
        super(context, FILE_NAME, null, DB_VERSION);
        this.context = context;
    }

    @SuppressLint("StaticFieldLeak")
    private static AppDb instance = null;
    private static SQLiteDatabase database = null;
    private static int clientCount = 0;

    static synchronized SQLiteDatabase getDatabase(final Context context) {
        if (context == null)
            return null;

        try {
            if (instance == null) {
                instance = new AppDb(context);
                database = instance.getWritableDatabase();
                clientCount = 1;
            } else {
                if (database == null)
                    database = instance.getWritableDatabase();

                clientCount++;
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return database;
    }

    static synchronized void releaseDatabase() {
        clientCount--;
        if (clientCount <= 0) {
            clientCount = 0;
            if (database != null)
                database.close();

            database = null;
            instance = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //Version 1
        database.execSQL(URLTable.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.print(TAG, "Upgrading from version " + oldVersion + " to version "
                + newVersion);
        for (int i = oldVersion + 1; i <= newVersion; i++) {
            Logger.print(TAG, "version is becoming current " + i);
            switch (i) {
//                case 2:
//                    db.execSQL("alter table " + TripTbl.TABLE_NAME + " add " + TripTbl.RATE_JSON + " text;");
//                    break;
            }
        }
    }

    static class URLTable {
        static final String TABLE_NAME = "url_table";
        static final String ID = "_id";
        static final String URL = "url";
        static final String[] TABLE_COLUMNS = {ID, URL};
        static final String CREATE_SQL = "create table " + TABLE_NAME
                + "(" + ID + " integer primary key autoincrement, "
                + URL + " integer not null);";
    }
}
