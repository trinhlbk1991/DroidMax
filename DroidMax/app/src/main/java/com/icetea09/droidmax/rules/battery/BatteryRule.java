package com.icetea09.droidmax.rules.battery;

import android.content.Intent;
import android.os.BatteryManager;

import com.icetea09.droidmax.rules.IRule;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class BatteryRule implements IRule {

    protected Intent mIntent;
    protected int mBatteryLevel;

    public BatteryRule(Intent intent, int batteryLevel) {
        mIntent = intent;
        mBatteryLevel = batteryLevel;
    }

    @Override
    public boolean isSatisfied() {
        int batteryLevel = mIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int batteryScale = mIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        return batteryLevel / (float) batteryScale <= mBatteryLevel;
    }

}
