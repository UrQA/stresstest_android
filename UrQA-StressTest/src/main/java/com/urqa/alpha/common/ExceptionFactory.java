package com.urqa.alpha.common;

import com.google.common.collect.Lists;
import com.urqa.alpha.exception.AccountsExceptionCommand;
import com.urqa.alpha.exception.AndroidExceptionCommand;
import com.urqa.alpha.exception.ArrayIndexOutOfBoundsExceptionCommand;
import com.urqa.alpha.exception.BrokenBarrierExceptionCommand;
import com.urqa.alpha.exception.ClassCastExceptionCommand;
import com.urqa.alpha.exception.DestroyFailedExceptionCommand;
import com.urqa.alpha.exception.DataFormatExceptionCommand;
import com.urqa.alpha.exception.FileNotFoundExceptionCommand;
import com.urqa.alpha.exception.FormatExceptionCommand;
import com.urqa.alpha.exception.NullPointerExceptionCommand;
import com.urqa.alpha.exception.NumberFormatExceptionCommand;

import java.util.List;
import java.util.Random;

/**
 * @author seunoh on 2014. 05. 07..
 */
public final class ExceptionFactory {

    private static volatile List<ExceptionCommand> sData = Lists.newArrayList();

    static {
        items().add(new AccountsExceptionCommand());
        items().add(new AndroidExceptionCommand());
        items().add(new ArrayIndexOutOfBoundsExceptionCommand());
        items().add(new BrokenBarrierExceptionCommand());
        items().add(new ClassCastExceptionCommand());
        items().add(new DataFormatExceptionCommand());
        items().add(new DestroyFailedExceptionCommand());
        items().add(new FileNotFoundExceptionCommand());
        items().add(new FormatExceptionCommand());
        items().add(new NullPointerExceptionCommand());
        items().add(new NumberFormatExceptionCommand());
    }


    public static ExceptionCommand getRandom() {
        Random random = new Random();
        return items().get(random.nextInt(items().size()));
    }

    public static List<ExceptionCommand> items() {
        return sData;
    }
}
