package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.content.Intent;

import com.icetea09.droidmax.rules.IRule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiRules implements IRule {

    public static final String TAG = WifiRules.class.getSimpleName();

    protected Intent mIntent;
    protected Context mContext;
    protected String mWifiName;

    @Override
    public boolean isSatisfied() {
        return false;
    }

    public WifiRules(String wifiName) {
        this.mWifiName = wifiName;
    }

    public void setIntent(Intent mIntent) {
        this.mIntent = mIntent;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public String convertToString() {
        return TAG;
    }
}
