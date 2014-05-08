package com.urqa.alpha.exception;

import android.util.Log;

/**
 * @author seunoh on 2014. 05. 07..
 */
public class NullPointCommand extends Command {

    private Object mObject;

    public NullPointCommand(String name) {
        super(name);
    }


    @Override
    public void execute() {
        Log.i("NULL", mObject.toString());
    }
}
