package br.com.weatherapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CityDb {
    public static final String TABLE_NAME = "tblCity";
    public static final String COLUMN_ID = "idCity";
    public static final String COLUMN_NAME = "cityName";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";

    private CityDbHelper dbHelper;

    public CityDb(Context context){
        this.dbHelper = new CityDbHelper(context);
    }

    public long insert(City city){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, city.name);
        values.put(COLUMN_LATITUDE, city.latitude);
        values.put(COLUMN_LONGITUDE, city.longitude);

        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        return db.insert(TABLE_NAME, null, values);
    }

    public List<City> getList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = { COLUMN_ID, COLUMN_NAME, COLUMN_LATITUDE, COLUMN_LONGITUDE };

        String sortOrder = COLUMN_NAME + " ASC";
        Cursor cursor = db.query(TABLE_NAME, projection, null,
                null, null, null, sortOrder);

        List<City> list = new ArrayList<>();
        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            Double lat = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LATITUDE));
            Double lng = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LONGITUDE));
            list.add(new City(id, name, lat, lng));
        }
        cursor.close();

        return list;
    }

    public int delete(long id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        return db.delete(TABLE_NAME, selection, selectionArgs);
    }

    public void close(){
        this.dbHelper.close();
    }
}
