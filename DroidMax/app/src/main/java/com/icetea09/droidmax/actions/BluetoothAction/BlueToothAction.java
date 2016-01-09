package com.icetea09.droidmax.actions.BluetoothAction;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import com.icetea09.droidmax.actions.IAction;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class BlueToothAction implements IAction{

    Context mContext;
    boolean mIsBluetoothEnable;
    public BlueToothAction(Context context, boolean isBluetoothEnable){
        this.mContext = context;
        this.mIsBluetoothEnable = isBluetoothEnable;
    }
    @Override
    public String convertToString() {
        return null;
    }

    @Override
    public void perform() {
        turnBluetooth(mIsBluetoothEnable);
    }

    private void turnBluetooth(boolean isBluetoothEnable){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (isBluetoothEnable){
            mBluetoothAdapter.enable();
        }
        else
            mBluetoothAdapter.disable();
    }
}
