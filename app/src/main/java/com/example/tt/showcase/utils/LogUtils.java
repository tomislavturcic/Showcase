package com.example.tt.showcase.utils;

import android.util.Log;

import com.example.tt.showcase.BuildConfig;

/**
 * Created by TT on 8.3.2018..
 * Showcase
 */

public class LogUtils {

    private static final String TAG = "Showcase";

    public static void log(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void log(String msg) {
        log(TAG, msg);
    }

    public static void error(Throwable throwable){
        if(BuildConfig.DEBUG){
            Log.w(TAG, throwable.getLocalizedMessage());
        }
    }

    public static void log(int value) {
        log(String.valueOf(value));
    }

    public static void log(float value) {
        log(String.valueOf(value));
    }

    public static void log(boolean value) {
        log(String.valueOf(value));
    }
}
