package com.urqa.alpha.exception;

import android.util.AndroidException;

import com.urqa.alpha.common.Command;

/**
 * @author seunoh on 2014. 05. 11..
 */
public class ArrayIndexOutOfBoundsExceptionCommand extends Command {

    public ArrayIndexOutOfBoundsExceptionCommand() {
        setName(AndroidException.class.getSimpleName());
    }

    @Override
    public void execute() throws Exception {
        throw new ArrayIndexOutOfBoundsException(name());
    }
}
