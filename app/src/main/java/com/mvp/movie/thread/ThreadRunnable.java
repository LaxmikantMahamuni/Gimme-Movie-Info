package com.mvp.movie.thread;

import android.util.Log;

/**
 * Created by hardik on 19/11/17.
 */

public class ThreadRunnable implements Runnable {
    private final String TAG = this.getClass().getSimpleName();

    private String variable = "initial value of variable";

    private ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "initial value";
        }
    };

    @Override
    public void run() {
        Log.d(TAG, "Thread local " + stringThreadLocal.get());
        Log.d(TAG, "variable " + variable);

        Log.d(TAG, "==================================");
        stringThreadLocal.set("new value");
        variable = "".concat(System.currentTimeMillis() + "");

        Log.d(TAG, "Thread local changed " + stringThreadLocal.get());
        Log.d(TAG, "variable changed" + variable);
    }
}
