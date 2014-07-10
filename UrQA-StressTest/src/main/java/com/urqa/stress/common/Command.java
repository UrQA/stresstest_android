package com.urqa.stress.common;

/**
 * @author seunoh on 2014. 05. 08..
 */
public abstract class Command implements ExceptionCommand {


    private String mName;

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String name() {
        return mName;
    }
}
