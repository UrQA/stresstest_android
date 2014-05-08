package com.urqa.alpha.exception;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author seunoh on 2014. 05. 07..
 */
public final class ExceptionFactory {

    private static volatile Map<String, ExceptionCommand> sMap = Maps.newHashMap();

    static {
        sMap.put(NullPointerException.class.getSimpleName(), new NullPointCommand(NullPointerException.class.getSimpleName()));
        sMap.put(FileNotFoundException.class.getSimpleName(), new FileNotFoundCommand(FileNotFoundException.class.getSimpleName()));
        sMap.put(ClassCastException.class.getSimpleName(), new ClassCastCommand(ClassCastException.class.getSimpleName()));
    }


    public static ExceptionCommand get(String key) {

        if (sMap.containsKey(key))
            return sMap.get(key);
        else
            throw new IllegalArgumentException("key is not found");
    }


    public static ExceptionCommand getRandom() {
        Random random = new Random();
        List<String> list = Lists.newArrayList(sMap.keySet());
        return get(list.get(random.nextInt(sMap.size())));
    }
}
