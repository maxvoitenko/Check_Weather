package com.voitenko.checkweather.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.voitenko.checkweather.Settings.ConstantsCW;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, ConstantsCW.DBNAME,
                null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ConstantsCW._TABLE_WITH_MARKERS + " (" +
                ConstantsCW._idMarker + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantsCW._latitude + " TEXT NOT NULL, " +
                ConstantsCW._longitude + " TEXT NOT NULL, " +
                ConstantsCW._city_in_map + " TEXT);");

        db.execSQL("CREATE TABLE " + ConstantsCW._TABLE_WITH_WEATHERS + " (" +
                ConstantsCW._idWeather + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantsCW._idMarkerInWeather + " INTEGER, " +
                ConstantsCW._city + " TEXT, " +
                ConstantsCW._country + " TEXT, " +
                ConstantsCW._description + " TEXT, " +
                ConstantsCW._main + " TEXT, " +
                ConstantsCW._temp + " TEXT, " +
                ConstantsCW._time + " TEXT);");
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}