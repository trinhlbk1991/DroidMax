package com.icetea09.droidmax.rules.battery;

import android.content.Intent;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class ChargerUnpluggedRule extends BatteryRule {

    public static final String TAG = ChargerUnpluggedRule.class.getName();

    public ChargerUnpluggedRule() {
        super(String.valueOf(-1));
    }

    @Override
    public boolean isSatisfied() {
        if (mIntent == null) {
            return false;
        }
        return mIntent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED);
    }

    @Override
    public String convertToString() {
        return TAG;
    }

    @Override
    public String getRuleDescription() {
        return "Charger was unplugged";
    }
}
