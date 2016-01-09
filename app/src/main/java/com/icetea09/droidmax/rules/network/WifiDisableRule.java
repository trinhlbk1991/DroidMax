package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiDisableRule extends WifiRule {

    String TAG = WifiDisableRule.class.getName();

    public WifiDisableRule() {
        super("");
    }

    @Override
    public boolean isSatisfied() {
        return isWifiEnable();
    }

    protected boolean isWifiEnable() {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        return wifiManager == null || !wifiManager.isWifiEnabled();
    }

    @Override
    public String convertToString() {
        return TAG;
    }

    @Override
    public String getRuleDescription() {
        return "Wifi turn off";
    }
}
