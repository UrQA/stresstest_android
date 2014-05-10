package com.urqa.common.JsonObj;

public abstract class JsonObj
{

    public JsonObj()
    {
    }

    public abstract String toJson();

    public abstract void fromJson(String s);
}
