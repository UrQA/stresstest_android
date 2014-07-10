package com.urqa.stress.exception;

import com.urqa.stress.common.Command;

/**
 * @author seunoh on 2014. 05. 08..
 */
public class ClassCastExceptionCommand extends Command {


    public ClassCastExceptionCommand() {

        setName(ClassCastException.class.getSimpleName());
    }


    @Override
    public void execute() throws Exception {
        throw new ClassCastException(name());
    }
}