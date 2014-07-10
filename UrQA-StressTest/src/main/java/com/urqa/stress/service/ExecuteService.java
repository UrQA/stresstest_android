package com.urqa.stress.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.urqa.common.StateData;
import com.urqa.stress.app.App;
import com.urqa.stress.app.AppPreferences;
import com.urqa.stress.common.SendErrorRunnable;
import com.urqa.clientinterface.URQAController;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteService extends Service {

    //private static final String URL = "http://ur-qa.com";
    //private static final String URL_PATH = "/urqa/client/send/exception";

    private Timer mTimer;

    public ExecuteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer(ExecuteService.class.getSimpleName(), true);
        URQAController.InitializeAndStartSession(getApplicationContext(), "6EC2E0F0");
        //final long PORT = App.getPreferences().getLong(AppPreferences.KEY_PORT, AppPreferences.DEFAULT_PORT);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Preconditions.checkNotNull(App.getPreferences());


        run();

        return Service.START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    public void run() {
        final long TIME = App.getPreferences().getLong(AppPreferences.KEY_TIME, AppPreferences.DEFAULT_TIME);
        final long COUNT = App.getPreferences().getLong(AppPreferences.KEY_COUNT, AppPreferences.DEFAULT_COUNT);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.i("SERVICE", StateData.ServerAddress);
                Log.i("SERVICE", String.format("URL :: %s, COUNT :: %s", url(), COUNT));
                for (int i = 0; i < COUNT; i++) {
                    ExecutorService service = Executors.newFixedThreadPool(Integer.MAX_VALUE);
                    service.execute(new SendErrorRunnable(url()));


                }
            }
        };
        mTimer.schedule(task, 0, TIME);
    }


    public String url() {
        long port = App.getPreferences().getLong(AppPreferences.KEY_PORT, AppPreferences.DEFAULT_PORT);

        //return URL + ":" + port + URL_PATH;

        return "";
    }

}
