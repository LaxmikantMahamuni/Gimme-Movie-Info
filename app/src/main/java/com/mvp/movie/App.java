package com.mvp.movie;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by hardik on 07/11/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
