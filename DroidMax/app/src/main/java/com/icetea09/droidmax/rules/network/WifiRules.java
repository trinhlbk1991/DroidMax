package com.icetea09.droidmax.rules.network;

import android.content.Context;
import android.content.Intent;

import com.icetea09.droidmax.rules.IRule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiRules implements IRule {

    protected Intent mIntent;
    protected Context mContext;
    protected String mWifiName;
    @Override
    public boolean isSatisfied() {
        return false;
    }

    public WifiRules(Context context, Intent intent, String wifiName){
        this.mContext = context;
        this.mIntent = intent;
        this.mWifiName = mWifiName;
    }
}
