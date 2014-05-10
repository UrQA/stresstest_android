package com.urqa.common.JsonObj;

import org.json.JSONException;
import org.json.JSONObject;

public class IDInstance extends JsonObj {
	public String idinstance;

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fromJson(String JsonString) {
		
		try {
			JSONObject obj = new JSONObject(JsonString);
			idinstance = obj.get("idinstance").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	
}
