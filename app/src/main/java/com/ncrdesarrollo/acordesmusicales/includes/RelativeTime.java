package com.ncrdesarrollo.acordesmusicales.includes;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class RelativeTime extends Application {

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    public static Number getTimeAgo(Context ctx) {

        SharedPreferences  mPref = ctx.getSharedPreferences("myPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();

        long time = mPref.getLong("timestamp", 0);

        //long time = 1614719156;

        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;

            return diff / HOUR_MILLIS;
            //return diff / MINUTE_MILLIS;

    }



}

