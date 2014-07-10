package com.urqa.stress.exception;

import android.accounts.AccountsException;

import com.urqa.stress.common.Command;

/**
 * @author seunoh on 2014. 05. 11..
 */
public class AccountsExceptionCommand extends Command {


    public AccountsExceptionCommand() {
        setName(AccountsException.class.getSimpleName());
    }

    @Override
    public void execute() throws Exception {
        throw new AccountsException(name());
    }
}
