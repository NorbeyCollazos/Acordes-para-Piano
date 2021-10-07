package com.ncrdesarrollo.acordesmusicales.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabaseAssets extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "AcordesPiano0002.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public MyDatabaseAssets(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getReadableDatabase();
    }

}
