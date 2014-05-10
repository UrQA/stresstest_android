package com.urqa.alpha.exception;

import android.nfc.FormatException;

import com.urqa.alpha.common.Command;

/**
 * @author seunoh on 2014. 05. 11..
 */
public class FormatExceptionCommand extends Command {

    public FormatExceptionCommand() {
        setName(FormatException.class.getSimpleName());
    }


    @Override
    public void execute() throws Exception {
        throw new FormatException(name());
    }
}
