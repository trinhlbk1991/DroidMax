package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiEnableRule extends WifiRules {

    String TAG = WifiEnableRule.class.getName();

    public WifiEnableRule() {
        super("");
    }

    @Override
    public boolean isSatisfied() {
        return isWifiEnable();
    }

    protected boolean isWifiEnable() {
        Log.d(TAG, "isWifiEnable");
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    @Override
    public String convertToString() {
        return TAG;
    }
}
