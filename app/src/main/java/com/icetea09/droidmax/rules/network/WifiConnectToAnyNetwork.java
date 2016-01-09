package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiConnectToAnyNetwork extends WifiRule {

    public static final String TAG = WifiConnectToAnyNetwork.class.getName();

    @Override
    public boolean isSatisfied() {
        return isNetworkConnected();
    }

    public WifiConnectToAnyNetwork() {
        super("");
    }

    protected boolean isNetworkConnected() {
        ConnectivityManager cm = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return netInfo != null && wifi.isConnected();
    }

    @Override
    public String convertToString() {
        return TAG;
    }

    @Override
    public String getRuleDescription() {
        return "Connect to any network";
    }
}
