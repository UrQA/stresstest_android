package com.urqa.stress.exception;

import com.urqa.stress.common.Command;

import java.io.FileNotFoundException;

/**
 * @author seunoh on 2014. 05. 07..
 */
public class FileNotFoundExceptionCommand extends Command {

    public FileNotFoundExceptionCommand() {

        setName(FileNotFoundException.class.getSimpleName());
    }

    @Override
    public void execute() throws Exception {
        throw new FileNotFoundException(name());
    }
}