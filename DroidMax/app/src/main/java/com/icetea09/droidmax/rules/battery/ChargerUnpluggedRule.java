package com.icetea09.droidmax.rules.battery;

import android.content.Intent;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class ChargerUnpluggedRule extends BatteryRule {

    public ChargerUnpluggedRule(Intent intent) {
        super(intent, 0);
    }

    @Override
    public boolean isSatisfied() {
        return mIntent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED);
    }

}
