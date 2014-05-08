package com.urqa.alpha.exception;

import android.util.Log;

/**
 * @author seunoh on 2014. 05. 08..
 */
public class ClassCastCommand extends Command {


    public ClassCastCommand(String name) {
        super(name);
    }


    @Override
    public void execute() {
        Integer i = 100;
        Object o = i;
        Double d = Double.valueOf(o.toString());
        Log.i("App", d.toString());
    }
}
