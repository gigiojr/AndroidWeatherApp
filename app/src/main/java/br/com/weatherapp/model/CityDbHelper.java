package br.com.weatherapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CityDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db.City";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CityDb.TABLE_NAME + " (" +
                    CityDb.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CityDb.COLUMN_NAME + " TEXT," +
                    CityDb.COLUMN_LATITUDE + " REAL,"  +
                    CityDb.COLUMN_LONGITUDE + " REAL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CityDb.TABLE_NAME;

    public CityDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
