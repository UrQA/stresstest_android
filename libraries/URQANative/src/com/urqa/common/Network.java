package com.urqa.common;

import java.util.concurrent.TimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

import android.util.Log;

import com.urqa.library.model.JsonInterface;

public class Network extends Thread {

	@Deprecated
	public enum Networkformula {
		GET, POST
	}

	public enum Method {
		GET, POST
	}

	private String mURL;
	private JsonInterface mJson;
	private Method mMethod;

	public void setNetwork(String url, JsonInterface json, Method method) {
		mURL = url;
		mJson = json;
		mMethod = method;
	}

	public void CallbackFunction(HttpResponse responseGet, HttpEntity resEntity) {

	}

	@Override
	public void run() {
		switch (mMethod) {
		case GET:
			GetSend();
			break;
		case POST:
			PostSend();
			break;
		}
	}

	private void GetSend() {
		try {
			HttpClient client = new DefaultHttpClient();
			setHttpParams(client.getParams());

			HttpGet get = new HttpGet(mURL);
			HttpResponse responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();

			if (resEntityGet != null) {
				CallbackFunction(responseGet, resEntityGet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void PostSend() {
		try {
			HttpClient client = new DefaultHttpClient();
			setHttpParams(client.getParams());

			HttpPost post = new HttpPost(mURL);

			post.setHeader("Content-Type", "application/json; charset=utf-8");

			String test = mJson.toJSONObject().toString();
			StringEntity input = new StringEntity(test, "UTF-8");

			post.setEntity(input);
			HttpResponse responsePOST = client.execute(post);
			HttpEntity resEntity = responsePOST.getEntity();

			int code = responsePOST.getStatusLine().getStatusCode();

			Log.i("UrQA", String.format("UrQA Response Code : %d", code));

			CallbackFunction(responsePOST, resEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setHttpParams(HttpParams params) {
		params.setParameter("http.protocol.expect-continue", false);
		params.setParameter("http.connection.timeout", 5000);
		params.setParameter("http.socket.timeout", 5000);
	}

}
