package com.icetea09.droidmax.rules.network;

import android.content.Context;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.rules.IRule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiRule implements IRule {

    public static final String TAG = WifiRule.class.getSimpleName();

    protected Context mContext;
    protected String mWifiName;

    @Override
    public boolean isSatisfied() {
        return false;
    }

    public WifiRule(String wifiName) {
        this.mWifiName = wifiName;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public String convertToString() {
        return TAG;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_wifi;
    }

    @Override
    public String getRuleDescription() {
        return TAG;
    }

    @Override
    public String getCategory() {
        return TAG;
    }
}
