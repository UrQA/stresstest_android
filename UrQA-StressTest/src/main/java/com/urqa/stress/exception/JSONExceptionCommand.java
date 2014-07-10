package com.urqa.stress.exception;

import com.urqa.stress.common.Command;

import org.json.JSONException;

/**
 * @author seunoh on 2014. 05. 11..
 */
public class JSONExceptionCommand extends Command {


    public JSONExceptionCommand() {
        setName(JSONException.class.getSimpleName());
    }


    @Override
    public void execute() throws Exception {
        throw new JSONException(name());
    }
}
