package com.urqa.alpha.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.urqa.Collector.ErrorReport;
import com.urqa.Collector.ErrorReportFactory;
import com.urqa.alpha.app.App;
import com.urqa.alpha.app.AppPreferences;
import com.urqa.alpha.common.ExceptionCommand;
import com.urqa.alpha.common.ExceptionFactory;
import com.urqa.alpha.common.FileHelper;
import com.urqa.alpha.common.Information;
import com.urqa.alpha.common.SendErrorProcess;
import com.urqa.rank.ErrorRank;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Timer;
import java.util.TimerTask;

public class ExecuteService extends Service {

    private static final String URL = "http://ur-qa.com";
    private static final String URL_PATH = "/urqa/client/send/exception";

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
                for (int i = 0; i < COUNT; i++) {
                    worker();
                }
            }
        };
        mTimer.schedule(task, 0, TIME);
    }

    public void worker() {
        DateTime now = DateTime.now(DateTimeZone.forID("Asia/Seoul"));
        Information information = new Information();
        try {
            ExceptionCommand command = ExceptionFactory.getRandom();

            information.setMillis(now.getMillis());
            information.setExceptionMessage(command.name());

            command.execute();
        } catch (Exception e) {
            ErrorReport report = ErrorReportFactory.CreateErrorReport(e, "", ErrorRank.Critical, App.getContext());
            SendErrorProcess process = new SendErrorProcess(report, url());
            process.start();
        } finally {
            Log.i("SERVICE", information.toString());
        }
    }


    public void workerFile(Information information) throws Exception {
        Gson gson = new Gson();
        FileHelper fileHelper = FileHelper.getInstance();
        fileHelper.append(gson.toJson(information));
    }


    public String url() {
        long port = App.getPreferences().getLong(AppPreferences.KEY_PORT, AppPreferences.DEFAULT_PORT);
        return URL + ":" + port + URL_PATH;
    }

}
