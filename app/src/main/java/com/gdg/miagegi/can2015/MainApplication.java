package com.gdg.miagegi.can2015;

import com.gdg.miagegi.can2015.utils.AppSharedPreferences;
import com.gdg.miagegi.can2015.utils.Constants;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.androidquery.AQuery;
import com.squareup.otto.Bus;

public class MainApplication extends Application {

    private static final Bus BUS = new Bus();

    private static Context context;

    private static AQuery mAQ;

    private static AppSharedPreferences mPrefs;

    private static final String TAG = MainApplication.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mPrefs = new AppSharedPreferences(context);

    }

    @Override
    public void onTerminate() {
        Log.d(TAG, "I have terminated");
        super.onTerminate();
        getBus().unregister(this);
    }

    public static AQuery getAQuery() {
        return getAQuery(MainApplication.getContext());
    }

    public static AQuery getAQuery(Context context) {
        if (mAQ == null) {
            mAQ = new AQuery(context);
        }
        return mAQ;
    }

    public static Bus getBus() {
        return BUS;
    }

    public static Context getContext() {
        return context;
    }

    public static AppSharedPreferences getPrefs() {
        return getPrefs(MainApplication.getContext());
    }

    public static AppSharedPreferences getPrefs(Context context) {
        if (mPrefs == null) {
            mPrefs = new AppSharedPreferences(context);
        }
        return mPrefs;
    }

    public static void startNewActivity(Context context, Class<? extends Activity> newTopActivityClass) {
        Intent intent = new Intent(context, newTopActivityClass);
        context.startActivity(intent);
    }

    public static void startNewTopActivity(Context context, Class<? extends Activity> newTopActivityClass) {
        Intent intent = new Intent(context, newTopActivityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            intent.addFlags(0x8000); // equal to Intent.FLAG_ACTIVITY_CLEAR_TASK
                                     // which is only available from API level
                                     // 11
        }
        context.startActivity(intent);
    }

    public static void startNewTopActivityFromIntent(Context context, Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            intent.addFlags(0x8000); // equal to Intent.FLAG_ACTIVITY_CLEAR_TASK
                                     // which is only available from API level
                                     // 11
        }
        context.startActivity(intent);
    }
}
