package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class NetworkConnectionRule extends WifiRules {

    public static final String TAG = NetworkConnectionRule.class.getName();

    @Override
    public boolean isSatisfied() {
        return isNetworkConnected();
    }

    public NetworkConnectionRule() {
        super("");
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
        return TAG;
    }
}
