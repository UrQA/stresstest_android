package com.urqa.alpha;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.urqa.alpha.app.App;
import com.urqa.alpha.app.AppPreferences;


public class MainActivity extends ActionBarActivity implements OnActivityCommandListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            execute(App.MAIN_URI);
        }
    }

    @Override
    public void execute(Uri uri) {
        if (App.RESULT_URI.equals(uri)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ResultFragment(), "RESULT")
                    .commit();
        } else if (App.RESULT_DETAIL_URI.equals(uri)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ResultDetailFragment(), "DETAIL")
                    .commit();
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