package com.icetea09.droidmax.actions;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.model.Rule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class BlueToothAction implements IAction {

    public static final String TAG = BlueToothAction.class.getName();

    protected Context mContext;
    protected boolean mIsBluetoothEnable;

    public BlueToothAction(String isBluetoothEnable) {
        this.mIsBluetoothEnable = Boolean.valueOf(isBluetoothEnable);
    }

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG)
                .append(Rule.ARGS_SEPARATOR)
                .append(mIsBluetoothEnable);
        return stringBuilder.toString();
    }

    @Override
    public void perform() {
        turnBluetooth(mIsBluetoothEnable);
    }

    private void turnBluetooth(boolean isBluetoothEnable) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (isBluetoothEnable) {
            mBluetoothAdapter.enable();
        } else
            mBluetoothAdapter.disable();
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_bluetooth;
    }

    @Override
    public String getActionDescription() {
        return "Turn " + (mIsBluetoothEnable ? "on" : "off") + " the bluetooth";
    }
}
