package com.voitenko.checkweather.Providers.Bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.voitenko.checkweather.DB.DbHelper;

public abstract class BaseDbProvider {
    private String tableName;
    private DbHelper dbHelper;

    public BaseDbProvider(Context context, String tableName) {
        this.tableName = tableName;
        this.dbHelper = new DbHelper(context);;
    }

    protected SQLiteDatabase getReadableDatabase(){
        return dbHelper.getReadableDatabase();
    }

    protected SQLiteDatabase getWritableDatabase(){
        return dbHelper.getWritableDatabase();
    }

    protected String getTableName() {
        return tableName;
    }
}
