package com.urqa.alpha.exception;

import android.util.Log;

import java.io.File;

/**
 * @author seunoh on 2014. 05. 07..
 */
public class FileNotFoundCommand extends Command {

    public FileNotFoundCommand(String name) {
        super(name);
    }

    @Override
    public void execute() {
        File f = new File("");
        Log.i("App", f.getName());
    }
}
