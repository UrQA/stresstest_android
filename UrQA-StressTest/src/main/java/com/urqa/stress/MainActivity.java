package com.urqa.stress;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.urqa.clientinterface.URQAController;
import com.urqa.stress.app.App;
import com.urqa.stress.app.AppPreferences;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URQAController.InitializeAndStartSession(getApplicationContext(), "D4973BD9");

        if (savedInstanceState == null) {
            execute(App.MAIN_URI);
        }
    }

    public void execute(Uri uri) {
        if (App.RESULT_URI.equals(uri)) {
            Log.i("UrQA", uri.toString());
        } else if (App.RESULT_DETAIL_URI.equals(uri)) {
            Log.i("UrQA", uri.toString());
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MainFragment(), "MAIN")
                    .commit();
        }
    }


    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("MAIN");
        if (fragment == null) {
            execute(App.MAIN_URI);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!App.getPreferences().getBoolean(AppPreferences.KEY_SERVICE, false)) {
            stopService(App.getServiceIntent());
        }
    }
}