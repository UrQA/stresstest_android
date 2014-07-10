package com.urqa.stress.common;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author seunoh on 2014. 05. 06..
 */
@Data
@Accessors(prefix = "m")
public class Information {

    @SerializedName("EXCEPTION_MESSAGE")
    private String mExceptionMessage;


    @SerializedName("MILLIS")
    private long mMillis;
}
