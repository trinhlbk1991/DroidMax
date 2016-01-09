package com.icetea09.droidmax.rules.battery;

import android.os.BatteryManager;

import com.icetea09.droidmax.model.Rule;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class LowBatteryRule extends BatteryRule {

    public static final String TAG = LowBatteryRule.class.getName();

    public LowBatteryRule(String batteryLevel) {
        super(batteryLevel);
    }

    @Override
    public boolean isSatisfied() {
        int batteryLevel = mIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int batteryScale = mIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        return batteryLevel / (float) batteryScale <= Integer.valueOf(mBatteryLevel);
    }

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG)
                .append(Rule.ARGS_SEPARATOR)
                .append(mBatteryLevel);
        return stringBuilder.toString();
    }

    @Override
    public String getRuleDescription() {
        return "Battery level lower than " + mBatteryLevel + "%";
    }
}
