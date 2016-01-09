package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.icetea09.droidmax.model.Rule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class NetworkConnectionRule extends WifiRules {

    public static final String TAG = NetworkConnectionRule.class.getName();

    @Override
    public boolean isSatisfied() {
        return isNetworkConnected();
    }

    public NetworkConnectionRule(String wifiName) {
        super(wifiName);
    }

    protected boolean isNetworkConnected() {
        ConnectivityManager cm = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (netInfo != null) {
            if (wifi.isConnected()) {
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
}
