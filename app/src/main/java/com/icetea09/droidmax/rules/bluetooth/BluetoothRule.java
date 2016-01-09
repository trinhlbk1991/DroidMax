package com.icetea09.droidmax.rules.bluetooth;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.rules.IRule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class BluetoothRule implements IRule{

    public static final String TAG = BluetoothRule.class.getSimpleName();

    @Override
    public boolean isSatisfied() {
        return false;
    }

    public BluetoothRule(){}

    @Override
    public String convertToString() {
        return TAG;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_bluetooth;
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
