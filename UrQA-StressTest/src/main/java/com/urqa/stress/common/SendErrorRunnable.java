package com.urqa.stress.common;

import android.util.Log;

import com.urqa.clientinterface.URQAController;
import com.urqa.common.StateData;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class SendErrorRunnable implements Runnable {
    private String mUrl;

    public SendErrorRunnable(String url) {
        this.mUrl = url;
    }

    @Override
    public void run() {
        worker();
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
            URQAController.SendException(e);
            //send(ErrorReportFactory.CreateErrorReport(e, "", ErrorRank.Critical, App.getContext()));
        } finally {

            Log.i("SERVICE", StateData.ServerAddress);
            Log.i("SERVICE", StateData.APIKEY);

            Log.i("SERVICE", information.toString());
        }
    }

//    public void send(ErrorReport report) {
//        try {
//            HttpClient client = new DefaultHttpClient();
//            HttpPost post = new HttpPost(mUrl);
//
//            post.setHeader("Content-Type", "application/json; charset=utf-8");
//            client.getParams().setParameter("http.protocol.expect-continue", false);
//            client.getParams().setParameter("http.connection.timeout", 50000);
//            client.getParams().setParameter("http.socket.timeout", 50000);
//
//            String test = report.ErrorData.toJson();
//            StringEntity input = new StringEntity(test, "UTF-8");
//
//            post.setEntity(input);
//            HttpResponse responsePOST = client.execute(post);
//
//            Log.i("SERVICE", String.format("[ %s ] >> %s || %s",
//                    Thread.currentThread().getName(),
//                    responsePOST.getStatusLine().getStatusCode(),
//                    responsePOST.getStatusLine().getReasonPhrase()));
//
//        } catch (Exception e) {
//            Log.e("SERVICE", String.format("[ %s ] >> %s", Thread.currentThread().getName(), e.getMessage()));
//        }
//    }
}