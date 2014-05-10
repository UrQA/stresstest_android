package com.urqa.alpha.exception;

import com.urqa.alpha.common.Command;

/**
 * @author seunoh on 2014. 05. 07..
 */
public class NullPointerExceptionCommand extends Command {


    public NullPointerExceptionCommand() {
        setName(NullPointerException.class.getSimpleName());
    }


    @Override
    public void execute() throws Exception {
        throw new NullPointerException(name());
    }
}