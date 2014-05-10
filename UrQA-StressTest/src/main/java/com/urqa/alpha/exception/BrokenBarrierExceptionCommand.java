package com.urqa.alpha.exception;

import com.urqa.alpha.common.Command;

import java.util.concurrent.BrokenBarrierException;

/**
 * @author seunoh on 2014. 05. 11..
 */
public class BrokenBarrierExceptionCommand extends Command {

    public BrokenBarrierExceptionCommand() {
        setName(BrokenBarrierException.class.getSimpleName());
    }

    @Override
    public void execute() throws Exception {
        throw new BrokenBarrierException(name());
    }
}
