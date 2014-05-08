package com.urqa.alpha.exception;

/**
 * @author seunoh on 2014. 05. 08..
 */
public abstract class Command implements ExceptionCommand {


    private String mName;

    protected Command(String name) {
        mName = name;
    }


    @Override
    public String name() {
        return mName;
    }
}
