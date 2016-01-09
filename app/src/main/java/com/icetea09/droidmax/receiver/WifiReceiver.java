package com.icetea09.droidmax.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiReceiver extends BroadcastReceiver {

    String TAG = WifiReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");

    }
}