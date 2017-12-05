package com.mvp.movie.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mvp.movie.Const;
import com.mvp.movie.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by hardik on 07/11/17.
 */

public class BoundService extends Service {

    private MyBinder myBinder = new MyBinder();
    private BoundService boundService = this;
    private String TAG = BoundService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, " service created ");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " service destroyed ");
    }

    public class MyBinder extends Binder {
        public BoundService getServiceInstance() {
            return boundService;
        }
    }

    @Override
    public boolean stopService(Intent name) {
        Log.d(TAG, " service stopped ");
        return super.stopService(name);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, " service onUnbind ");
        shouldStopService(true);
        return super.onUnbind(intent);
    }

    public void shouldStopService(boolean _shouldStopService) {

        String currentTimeInString = getDurationBreakdown(System.currentTimeMillis());
        Log.d(TAG, _shouldStopService ? " service is about to get killed  " : "service is now started" + currentTimeInString);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(_shouldStopService ? Const.END_TIME : Const.START_TIME, currentTimeInString).apply();
        if (_shouldStopService) {
            stopSelf();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public static String getDurationBreakdown(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        return (String.valueOf(days) +
                " Days " +
                hours +
                " Hours " +
                minutes +
                " Minutes " +
                seconds +
                " Seconds");
    }
}
