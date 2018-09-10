package com.voitenko.checkweather.Providers.Bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.voitenko.checkweather.Data.AllWeather;
import com.voitenko.checkweather.Settings.ConstantsCW;

import java.util.ArrayList;

public class WeatherProvider extends BaseDbProvider {

    public WeatherProvider(Context context) {
        super(context, ConstantsCW._TABLE_WITH_WEATHERS);
    }


    public void insetrItem(AllWeather allWeather){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantsCW._idMarkerInWeather, allWeather.getIdMarker());
        contentValues.put(ConstantsCW._city, allWeather.getName());
        contentValues.put(ConstantsCW._country, allWeather.getSysCountry());
        contentValues.put(ConstantsCW._description, allWeather.getWeatherDescription());
        contentValues.put(ConstantsCW._main, allWeather.getWeatherMain());
        contentValues.put(ConstantsCW._temp, allWeather.getMainTemp());
        contentValues.put(ConstantsCW._time, allWeather.getTime());
        db.insert(getTableName(), null, contentValues);
        db.close();
    }

    public ArrayList<AllWeather> getItemsByIdMarker(long idMarker){
        ArrayList<AllWeather> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selection = ConstantsCW._idMarkerInWeather + "=?";
        String[] selectionArgs = new String[]{Long.toString(idMarker)};

        Cursor cursor = db.query(getTableName(), null,
                selection, selectionArgs,
                null, null, null);

        if (cursor != null) {
            int idColumn = cursor.getColumnIndex(ConstantsCW._idWeather);
            int idMarkerColumn = cursor.getColumnIndex(ConstantsCW._idMarkerInWeather);
            int cityColumn = cursor.getColumnIndex(ConstantsCW._city);
            int countryColumn = cursor.getColumnIndex(ConstantsCW._country);
            int descriptColumn = cursor.getColumnIndex(ConstantsCW._description);
            int mainColumn = cursor.getColumnIndex(ConstantsCW._main);
            int tempColumn = cursor.getColumnIndex(ConstantsCW._temp);
            int timeColumn = cursor.getColumnIndex(ConstantsCW._time);
            if (cursor.moveToFirst()) {
                do{
                    long id = cursor.getLong(idColumn);
                    Long idMarkers = cursor.getLong(idMarkerColumn);
                    String city = cursor.getString(cityColumn);
                    String country = cursor.getString(countryColumn);
                    String descript = cursor.getString(descriptColumn);
                    String main = cursor.getString(mainColumn);
                    String temp = cursor.getString(tempColumn);
                    String time = cursor.getString(timeColumn);

                    AllWeather allWeather = new AllWeather(idMarkers, city, country, descript, main, temp, time);
                    result.add(allWeather);
                } while(cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return result;
    }


}
