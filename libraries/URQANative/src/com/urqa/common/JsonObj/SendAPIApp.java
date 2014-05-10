package com.urqa.common.JsonObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SendAPIApp extends JsonObj {
	public String apikey;
	public String appversion;
	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("apikey", apikey);
			jsonObject.put("appversion", appversion);
			
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
