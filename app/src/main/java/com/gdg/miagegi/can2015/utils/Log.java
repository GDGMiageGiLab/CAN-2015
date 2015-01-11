package com.gdg.miagegi.can2015.utils;

public class Log {

    public static void d(String tag, String msg, Throwable tr) {
        if (!Constants.isProd()) {
            android.util.Log.d(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (!Constants.isProd()) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (!Constants.isProd()) {
            android.util.Log.e(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (!Constants.isProd()) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (!Constants.isProd()) {
            android.util.Log.i(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (!Constants.isProd()) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (!Constants.isProd()) {
            android.util.Log.w(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (!Constants.isProd()) {
            android.util.Log.i(tag, msg);
        }
    }
}
