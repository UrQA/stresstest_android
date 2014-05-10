package com.urqa.alpha.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.urqa.alpha.service.Time;

/**
 * @author seunoh on 2014. 05. 06..
 */
public class AppPreferences implements Preferences {

    public static final String KEY_API = "API_KEY";
    public static final String KEY_PORT = "PORT";
    public static final String KEY_SERVICE = "SERVICE";
    public static final String KEY_TIME = "TIME";
    public static final String KEY_COUNT = "COUNT";

    public static final long DEFAULT_TIME = Time.TEN_MINUTES.getMilliseconds();
    public static final long DEFAULT_PORT = 80L;
    public static final String DEFAULT_KEY = "AD5D05E8";
    public static final int DEFAULT_COUNT = 1;

    private SharedPreferences mPreferences;


    public AppPreferences init(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return this;
    }

    private SharedPreferences getPreferences() {
        return mPreferences;
    }


    @Override
    public String getString(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }


    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }


    @Override
    public boolean putString(String key, String value) {
        return getPreferences().edit().putString(key, value).commit();
    }

    @Override
    public boolean putLong(String key, long value) {
        return getPreferences().edit().putLong(key, value).commit();
    }

    @Override
    public boolean putBoolean(String key, boolean value) {
        return getPreferences().edit().putBoolean(key, value).commit();
    }
}
