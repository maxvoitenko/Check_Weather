package com.voitenko.checkweather.Providers.Bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.voitenko.checkweather.Data.Marker;
import com.voitenko.checkweather.Settings.ConstantsCW;

import java.util.ArrayList;

public class MarkersProvider extends BaseDbProvider{

    public MarkersProvider(Context context) {
        super(context, ConstantsCW._TABLE_WITH_MARKERS);
    }

    public ArrayList<Marker> getAllMarkers(){
        ArrayList<Marker> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(getTableName(), null, null, null,
                null, null, null);
        if (cursor != null) {
            int idPos = cursor.getColumnIndex(ConstantsCW._idMarker);
            int latitudePos = cursor.getColumnIndex(ConstantsCW._latitude);
            int longitudePos = cursor.getColumnIndex(ConstantsCW._longitude);
            int cityInMapPos = cursor.getColumnIndex(ConstantsCW._city_in_map);

            if (cursor.moveToFirst()) {
                do{
                    long id = cursor.getLong(idPos);
                    String latitude = cursor.getString(latitudePos);
                    String longitude = cursor.getString(longitudePos);
                    String cityInMap = cursor.getString(cityInMapPos);
                    Marker marker = new Marker(id,Double.parseDouble(latitude),Double.parseDouble(longitude), cityInMap);
                    result.add(marker);
                } while(cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return result;
    }

    public void insetrItem(Marker marker){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put(ConstantsCW._idMarker, marker.getId());
        contentValues.put(ConstantsCW._latitude, marker.getLatitude());
        contentValues.put(ConstantsCW._longitude, marker.getLongitude());
        contentValues.put(ConstantsCW._city_in_map, marker.getCityInMap());
        db.insert(getTableName(), null, contentValues);
        db.close();
    }

    public Marker getItemById(long id){
        Marker marker = null;
        SQLiteDatabase db = getReadableDatabase();

        String selection = ConstantsCW._idMarker + "=?";
        String[] selectionArgs = new String[]{Long.toString(id)};

        Cursor cursor = db.query(getTableName(), null,
                selection, selectionArgs,
                null, null, null);
        if (cursor != null) {
            int latitudePos = cursor.getColumnIndex(ConstantsCW._latitude);
            int longitudePos = cursor.getColumnIndex(ConstantsCW._longitude);
            int cityInMapPos = cursor.getColumnIndex(ConstantsCW._city_in_map);

            if (cursor.moveToFirst()) {
                String latitude = cursor.getString(latitudePos);
                String longitude = cursor.getString(longitudePos);
                String cityInMap = cursor.getString(cityInMapPos);
                marker = new Marker(id,Double.parseDouble(latitude),Double.parseDouble(longitude), cityInMap);
            }
            cursor.close();
        }
        db.close();
        return marker;
    }
}
