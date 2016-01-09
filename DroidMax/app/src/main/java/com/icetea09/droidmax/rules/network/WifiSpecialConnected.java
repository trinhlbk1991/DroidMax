package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiSpecialConnected extends WifiRules{
    String TAG = WifiSpecialConnected.class.getSimpleName();

    @Override
    public boolean isSatisfied() {
        return isWifiEnable();
    }

    public WifiSpecialConnected(Context context, Intent intent, String wifiName) {
        super(context, intent, wifiName);
    }

    protected boolean isWifiEnable(){
        WifiManager wifiManager = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
        boolean isWifiConnected = wifiManager != null && wifiManager.isWifiEnabled();

        if (isWifiConnected) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            Log.d(TAG, "Wifi Name: " + wifiInfo.getSSID());
//            Log.d(TAG, "Wifi Special Name: " + mWifiName);
            if (wifiInfo.getSSID().equals(mWifiName)) {
                return true;
            }
        }
        return false;
    }
}
