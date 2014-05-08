package com.urqa.alpha.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.urqa.alpha.app.App;
import com.urqa.alpha.app.AppPreferences;
import com.urqa.alpha.common.FileHelper;
import com.urqa.alpha.common.Information;
import com.urqa.alpha.exception.ExceptionCommand;
import com.urqa.alpha.exception.ExceptionFactory;
import com.urqa.clientinterface.URQAController;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Timer;
import java.util.TimerTask;

public class ExecuteService extends Service {

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
        FileHelper.getInstance().init();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Preconditions.checkNotNull(App.getPreferences());


        final long TIME = App.getPreferences().getLong(AppPreferences.KEY_TIME, AppPreferences.DEFAULT_TIME);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                DateTime now = DateTime.now(DateTimeZone.forID("Asia/Seoul"));
                try {
                    Gson gson = new Gson();
                    FileHelper fileHelper = FileHelper.getInstance();


                    ExceptionCommand command = ExceptionFactory.getRandom();
                    Information information = new Information();
                    information.setMillis(now.getMillis());
                    information.setExceptionMessage(command.name());
                    Log.i("SERVICE", information.toString());
                    fileHelper.append(gson.toJson(information));
                    command.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                    URQAController.SendException(e, now.toString("yyyy-MM-dd hh:mm:ss"));
                }

            }
        };

        mTimer.schedule(task, 0, TIME);
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
