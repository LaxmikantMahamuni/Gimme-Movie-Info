package com.mvp.movie;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * To check network connections states
 * Created by hardik on 06/01/16.
 */
public class ConnectionManager implements Connectivity {

    public ConnectionManager() {
    }

    /**
     * Get the network info
     *
     * @return Network info
     */
    private static NetworkInfo getNetworkInfo() {
        final Context context = App.instance();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * Check if there is any connectivity
     *
     * @return Has network connected or not
     */
    public boolean isConnected() {
        NetworkInfo info = getNetworkInfo();
        return info != null && info.isConnected();
    }

    public static boolean isNetworkAvailable(){
        NetworkInfo info = getNetworkInfo();
        return info != null && info.isConnected();
    }
}