package com.urqa.alpha.common;

/**
 * @author seunoh on 2014. 05. 07..
 */
public interface ExceptionCommand {


    public String name();

    public void execute() throws Exception;
}
