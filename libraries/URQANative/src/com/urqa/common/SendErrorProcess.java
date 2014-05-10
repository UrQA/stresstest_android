package com.urqa.common;

import com.urqa.Collector.ErrorReport;
import com.urqa.common.JsonObj.IDInstance;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class SendErrorProcess extends Thread {
    private ErrorReport report;
    private String url;

    public SendErrorProcess(ErrorReport report, String url) {
        // TODO Auto-generated constructor stub
        this.report = report;
        this.url = url;
    }

    @Override
    public void run() {
        try {


            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(StateData.ServerAddress + url);

            post.setHeader("Content-Type", "application/json; charset=utf-8");
            client.getParams().setParameter("http.protocol.expect-continue", false);
            client.getParams().setParameter("http.connection.timeout", 30000);
            client.getParams().setParameter("http.socket.timeout", 30000);

            String test = report.ErrorData.toJson();
            StringEntity input = new StringEntity(test, "UTF-8");

            post.setEntity(input);
            HttpResponse responsePOST = client.execute(post);
            HttpEntity resEntity = responsePOST.getEntity();

            if (StateData.TransferLog == false)
                return;

            String jsondata = "";
            try {
                jsondata = EntityUtils.toString(resEntity);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

                IDInstance idinstance = new IDInstance();
                idinstance.fromJson(jsondata);

            try {

                HttpClient logclient = new DefaultHttpClient();
                HttpPost logpost = new HttpPost(StateData.ServerAddress +
                        "client/send/exception/log/" +
                        idinstance.idinstance);

                logclient.getParams().setParameter("http.protocol.expect-continue", false);
                logclient.getParams().setParameter("http.connection.timeout", 30000);
                logclient.getParams().setParameter("http.socket.timeout", 30000);

                // 1. 파일의 내용을 body 로 설정함
                logpost.setHeader("Content-Type", "text/plain; charset=utf-8");
                StringEntity entity = new StringEntity(report.LogData, "UTF-8");
                logpost.setEntity(entity);


                HttpResponse response = logclient.execute(logpost);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}