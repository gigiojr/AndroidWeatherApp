package br.com.weatherapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    private static final String PREFS_NAME = "preferences";

    public static final String USER_UNITY = "unity";
    public static final String USER_LANGUAGE = "language";

    public static String setPreference(Context context, String item, String value){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(item, value);
        editor.apply();
        return value;
    }

    public static String getPreference(Context context, String item){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, 0);
        return preferences.getString(item, "");
    }
}
