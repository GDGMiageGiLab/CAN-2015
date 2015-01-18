package com.gdg.miagegi.can2015.service;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.gdg.miagegi.can2015.utils.Utils;


public class RefreshService extends Service {
    public static class RefreshAlarmReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            context.startService(new Intent(context, DataFetchService.class));
        }
    }

    public static final String SIXTY_MINUTES = "3600000";

    private AlarmManager alarmManager;

    // private final OnSharedPreferenceChangeListener listener = new OnSharedPreferenceChangeListener() {
    //     @Override
    //     public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    //        if (Utils.REFRESH_INTERVAL.equals(key)) {
    //            restartTimer(false);
    //      }
    //   }
    //};
    private PendingIntent timerIntent;

    @Override
    public IBinder onBind(Intent intent) {
        onRebind(intent);
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // Utils.registerOnPrefChangeListener(listener);
        restartTimer(true);
    }

    @Override
    public void onDestroy() {
        if (timerIntent != null) {
            alarmManager.cancel(timerIntent);
        }
        //Utils.unregisterOnPrefChangeListener(listener);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true; // we want to use rebind
    }

    private void restartTimer(boolean created) {
        if (timerIntent == null) {
            timerIntent = PendingIntent.getBroadcast(this, 0, new Intent(this, RefreshAlarmReceiver.class), 0);
        } else {
            alarmManager.cancel(timerIntent);
        }

        int time = 3600000;
        try {
            time = Math.max(60000, Integer.parseInt(Utils.getString(Utils.REFRESH_INTERVAL, SIXTY_MINUTES)));
        } catch (final Exception ignored) {
        }

        long initialRefreshTime = SystemClock.elapsedRealtime() + 10000;

        if (created) {
            final long lastRefresh = Utils.getLong(Utils.LAST_SCHEDULED_REFRESH, 0);

            if (lastRefresh > 0) {
                // this indicates a service restart by the system
                initialRefreshTime = Math.max(SystemClock.elapsedRealtime() + 10000, lastRefresh + time);
            }
        }

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, initialRefreshTime, time, timerIntent);
    }
}