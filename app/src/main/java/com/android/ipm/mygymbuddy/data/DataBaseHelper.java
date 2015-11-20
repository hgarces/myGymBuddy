package com.android.ipm.mygymbuddy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by henrique on 18/11/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "mgb.db";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
