package com.icetea09.droidmax.rules.battery;

import android.content.Intent;
import android.os.BatteryManager;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class ChargerPluggedRule extends BatteryRule {

    public ChargerPluggedRule(Intent intent) {
        super(intent, 0);
    }

    @Override
    public boolean isSatisfied() {
        int plugged = mIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;
    }
}
