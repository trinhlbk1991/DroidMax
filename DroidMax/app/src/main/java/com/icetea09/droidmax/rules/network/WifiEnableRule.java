package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiEnableRule extends WifiRules {

    String TAG = WifiEnableRule.class.getSimpleName();

    public WifiEnableRule(Context context, Intent intent) {
        super(context, intent);
    }

    @Override
    public boolean isSatisfied() {
        return isWifiEnable();
    }

    protected boolean isWifiEnable(){
        Log.d(TAG, "isWifiEnable");
        WifiManager wifiManager = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
        return wifiManager != null && wifiManager.isWifiEnabled();

//        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo mWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        return mWifi.isConnected();
    }
}
