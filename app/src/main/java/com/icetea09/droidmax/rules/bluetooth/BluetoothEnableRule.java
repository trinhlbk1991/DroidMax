package com.icetea09.droidmax.rules.bluetooth;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class BluetoothEnableRule extends BluetoothRule {

    public static final String TAG = BluetoothEnableRule.class.getName();

    @Override
    public boolean isSatisfied() {
        return isBluetoothEnable();
    }

    public BluetoothEnableRule() {
    }

    protected boolean isBluetoothEnable() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        } else {
            if (mBluetoothAdapter.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String convertToString() {
        return TAG;
    }

    @Override
    public String getRuleDescription() {
        return "Bluetooth turns on";
    }
}
