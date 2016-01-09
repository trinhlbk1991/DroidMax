package com.icetea09.droidmax.rules.battery;

import android.content.Intent;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class LowBatteryRule extends BatteryRule {

    public LowBatteryRule(Intent intent, int batteryLevel) {
        super(intent, batteryLevel);
    }

    @Override
    public boolean isSatisfied() {
        return super.isSatisfied();
    }
}
