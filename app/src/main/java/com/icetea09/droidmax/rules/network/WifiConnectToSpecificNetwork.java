package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.icetea09.droidmax.model.Rule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiConnectToSpecificNetwork extends WifiRule {

    String TAG = WifiConnectToSpecificNetwork.class.getName();

    @Override
    public boolean isSatisfied() {
        return isWifiEnable();
    }

    public WifiConnectToSpecificNetwork(String wifiName) {
        super(wifiName);
    }

    protected boolean isWifiEnable() {

        if (mContext == null) {
            return false;
        }

        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        boolean isWifiConnected = wifiManager != null && wifiManager.isWifiEnabled();

        if (isWifiConnected) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            Log.d(TAG, "Wifi Name: " + wifiInfo.getSSID());
            if (wifiInfo.getSSID().equals(mWifiName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG)
                .append(Rule.ARGS_SEPARATOR)
                .append(mWifiName);
        return stringBuilder.toString();
    }

    @Override
    public String getRuleDescription() {
        return "Connect to network with name: " + mWifiName;
    }
}
