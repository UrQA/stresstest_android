package com.urqa.common.JsonObj;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.urqa.common.StateData;
import com.urqa.eventpath.EventPath;

public class ErrorSendData extends JsonObj {
	public ErrorSendData() {
		// TODO Auto-generated constructor stub
		sdkversion = StateData.SDKVersion;
		locale = "";
		apikey = "";
		datetime = "unknown";
		device = "unknown";
		country = "unknown";
		errorname = "";
		errorclassname = "";
		linenum = -1;
		callstack = "";
		appversion = "unknown";
		osversion = "unknown";
		gpson = 0; // int 0 = off ,1 = on
		wifion = 0;
		mobileon = 0;
		scrwidth = -1;
		scrheight = -1;
		lastactivity = "";

		// CallStackFileName = null;
		// LogFileName = null;
		batterylevel = -1;
		availsdcard = -1;
		rooted = 0; // int 0 = off ,1 = on
		appmemtotal = -1; // mega단위로 int.
		appmemfree = -1;
		appmemmax = -1;
		kernelversion = "";
		xdpi = -1;
		ydpi = -1;
		scrorientation = -1;
		sysmemlow = 0; // int 0 = not 1 = ok
		tag = "";
		rank = -1;
	}

	public String sdkversion;
	public String locale;

	public String tag;

	public int rank;

	/**
	 * CallStack데이터
	 */
	public String callstack;

	/**
	 * APIKEY
	 */
	public String apikey;
	/**
	 * 에러 발생 시간
	 */
	public String datetime;
	/**
	 * 핸드폰 모델 명
	 */
	public String device;
	/**
	 * 국가명
	 */
	public String country;
	/**
	 * 에러 이름
	 */
	public String errorname;
	/**
	 * 에러가 발생한 클래스 이름 ClassName 클래스 이름
	 */
	public String errorclassname;
	/**
	 * Error Code Line
	 */
	public int linenum;
	/**
	 * 앱 버젼
	 */
	public String appversion;
	/**
	 * OS 버젼
	 */
	public String osversion;
	// public String CallStack;
	// public String UserLog;

	/**
	 * GPS On/Off
	 */
	public int gpson;
	/**
	 * WiFi On/Off
	 */
	public int wifion;
	/**
	 * MobileNetwork(3G) On/Off
	 */
	public int mobileon;
	/**
	 * 화면 가로 크기
	 */
	public int scrwidth;
	/**
	 * 화면 세로 크기
	 */
	public int scrheight;

	/**
	 * 배터리 잔량
	 */
	public int batterylevel;

	/**
	 * 사용가능한 용량 //mega단위로 int
	 */
	public int availsdcard;

	/**
	 * 루트 되었는지 안되었는지
	 */
	public int rooted;

	/**
	 * 사용량
	 */
	public int appmemtotal;

	/**
	 * 남은량
	 */
	public int appmemfree;

	/**
	 * 최대량
	 */
	public int appmemmax;

	/**
	 * 리눅스 커널 버전
	 */
	public String kernelversion;

	/**
	 * XDPI
	 */
	public float xdpi;
	/**
	 * YDPI
	 */
	public float ydpi;

	/**
	 * 0,2 세로 1,3 가로
	 */
	public int scrorientation;

	/**
	 * 시스템 메모리가 부족한가 안부족한가
	 */
	public int sysmemlow;

	public String lastactivity;

	public List<EventPath> eventpaths;

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("sdkversion", sdkversion);
			jsonObject.put("locale", locale);
			jsonObject.put("tag", tag);
			jsonObject.put("rank", rank);
			jsonObject.put("callstack", callstack);
			jsonObject.put("apikey", apikey);
			jsonObject.put("datetime", datetime);
			jsonObject.put("device", device);
			jsonObject.put("country", country);
			jsonObject.put("errorname", errorname);
			jsonObject.put("errorclassname", errorclassname);
			jsonObject.put("linenum", linenum);
			jsonObject.put("appversion", appversion);
			jsonObject.put("osversion", osversion);
			jsonObject.put("gpson", gpson);
			jsonObject.put("wifion", wifion);
			jsonObject.put("mobileon", mobileon);
			jsonObject.put("scrwidth", scrwidth);
			jsonObject.put("scrheight", scrheight);
			jsonObject.put("batterylevel", batterylevel);
			jsonObject.put("availsdcard", availsdcard);
			jsonObject.put("rooted", rooted);
			jsonObject.put("appmemtotal", appmemtotal);
			jsonObject.put("appmemfree", appmemfree);
			jsonObject.put("appmemmax", appmemmax);
			jsonObject.put("kernelversion", kernelversion);
			jsonObject.put("xdpi", xdpi);
			jsonObject.put("ydpi", ydpi);
			jsonObject.put("scrorientation", scrorientation);
			jsonObject.put("sysmemlow", sysmemlow);
			jsonObject.put("lastactivity", lastactivity);
			////////////////Event Path 계산////////////////////
			JSONArray eventpath = new JSONArray();
			for(int i = 0 ; i < eventpaths.size(); i++)
			{
				JSONObject event = new JSONObject();
				
				event.put("datetime", eventpaths.get(i).getDatetime());
				event.put("classname", eventpaths.get(i).getClassName());
				event.put("methodname", eventpaths.get(i).getMethodName());
				event.put("label", eventpaths.get(i).getLabel());
				event.put("linenum", eventpaths.get(i).getLine());
				
				eventpath.put(event);
			}
			jsonObject.put("eventpaths", eventpath);
			
			return jsonObject.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public void fromJson(String JsonString) {
		// TODO Auto-generated method stub

	}

}
