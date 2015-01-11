package com.gdg.miagegi.can2015.model;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class Model {

    private static DatabaseHelper databaseHelper = null;

    @SuppressWarnings("rawtypes")
    public static void logException(Class clazz, Exception e) {
        Log.e(clazz.getName(), "Database Exception", e);
    }


    public static DatabaseHelper getHelper(Context context) {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

}
