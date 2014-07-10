package com.urqa.stress.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @author seunoh on 2014. 05. 06..
 */
public class App extends Application {

    public static final Uri MAIN_URI = Uri.EMPTY;
    public static final Uri RESULT_URI = Uri.parse("result");
    public static final Uri RESULT_DETAIL_URI = Uri.parse("detail");

    private static Context sContext = null;
    private static AppPreferences sPreferences = null;

    private static Intent sServiceIntent;


    @Override
    public void onCreate() {

        sContext = getApplicationContext();
        sPreferences = new AppPreferences().init(getApplicationContext());
        sServiceIntent = new Intent("com.urqa.stress.action.SERVICE");
    }


    public static Context getContext() {
        return sContext;
    }

    public static AppPreferences getPreferences() {
        return sPreferences;
    }

    public static Intent getServiceIntent() {
        return sServiceIntent;
    }
}