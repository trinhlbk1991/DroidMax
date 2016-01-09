package com.icetea09.droidmax.rules.battery;

import android.content.Intent;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.rules.IRule;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class BatteryRule implements IRule {

    public static final String TAG = BatteryRule.class.getSimpleName();

    protected Intent mIntent;
    protected String mBatteryLevel;

    public BatteryRule(String batteryLevel) {
        mBatteryLevel = batteryLevel;
    }

    public void setIntent(Intent intent) {
        mIntent = intent;
    }

    @Override
    public boolean isSatisfied() {
        return false;
    }

    @Override
    public String convertToString() {
        return TAG;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_battery;
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
