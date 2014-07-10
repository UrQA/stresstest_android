package com.urqa.library.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Authentication implements JsonInterface {
    private String mKey;
    private String mAppVersion;

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("apikey", getKey());
            object.put("appversion", getAppVersion());

        } catch (JSONException e) {
        }
        return object;
    }

    /**
     * @return the mKey
     */
    public String getKey() {
        return mKey;
    }

    /**
     * @param key the mKey to set
     */
    public void setKey(String key) {
        this.mKey = key;
    }

    /**
     * @return the mAppVersion
     */
    public String getAppVersion() {
        return mAppVersion;
    }

    /**
     * @param appVersion the mAppVersion to set
     */
    public void setAppVersion(String appVersion) {
        this.mAppVersion = appVersion;
    }

}
