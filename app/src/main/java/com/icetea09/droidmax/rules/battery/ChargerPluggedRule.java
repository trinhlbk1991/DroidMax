package com.icetea09.droidmax.rules.battery;

import android.os.BatteryManager;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class ChargerPluggedRule extends BatteryRule {

    public static final String TAG = ChargerPluggedRule.class.getName();

    public ChargerPluggedRule() {
        super(String.valueOf(-1));
    }

    @Override
    public boolean isSatisfied() {
        if (mIntent == null) {
            return false;
        }
        int plugged = mIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
    }

    @Override
    public String convertToString() {
        return TAG;
    }

    @Override
    public String getRuleDescription() {
        return "Charger was plugged";
    }
}
