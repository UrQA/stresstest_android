package com.urqa.alpha.service;

import com.urqa.alpha.R;
import com.urqa.alpha.app.App;

/**
 * @author seunoh on 2014. 05. 06..
 */
public enum Time {

    ONE_MINUTES(R.string.time_one_minute, 60000), //
    TEN_MINUTES(R.string.time_ten_minutes, 600000), //
    HALF_HOUR(R.string.time_half_hour, 1800000), //
    ONE_HOUR(R.string.time_one_hour, 3600000), //
    THREE_HOURS(R.string.time_three_hour, 10800000), //
    ;

    private final int mResourceId;
    private final long mMilliseconds;

    Time(int resourceId, long milliseconds) {
        mResourceId = resourceId;
        mMilliseconds = milliseconds;
    }

    public long getMilliseconds() {
        return mMilliseconds;
    }


    @Override
    public String toString() {
        return App.getContext().getString(mResourceId);
    }
}
