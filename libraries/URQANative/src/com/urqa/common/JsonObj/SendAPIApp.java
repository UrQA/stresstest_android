package com.urqa.common.JsonObj;

import org.json.JSONException;
import org.json.JSONObject;

import com.urqa.library.model.JsonInterface;

import java.util.Map;

public class SendAPIApp extends JsonObj implements JsonInterface {
	public String apikey;
	public String appversion;

	@Override
	public String toJson() {
		return toJSONObject().toString();

	}

	@Override
	public void fromJson(String JsonString) {

	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		try {
			object.put("apikey", apikey);
			object.put("appversion", appversion);

		} catch (JSONException e) {
		}
		return object;
	}

}
