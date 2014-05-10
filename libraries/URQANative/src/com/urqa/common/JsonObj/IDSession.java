package com.urqa.common.JsonObj;

import org.json.JSONException;
import org.json.JSONObject;

public class IDSession extends JsonObj{
	public String idsession;

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fromJson(String JsonString) {
		// TODO Auto-generated method stub
		
		try {
			JSONObject obj = new JSONObject(JsonString);
			idsession = obj.get("idsession").toString(); 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
