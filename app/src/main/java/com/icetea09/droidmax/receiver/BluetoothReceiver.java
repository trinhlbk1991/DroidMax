package com.icetea09.droidmax.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.icetea09.droidmax.MainActivity;
import com.icetea09.droidmax.database.RulesDataSource;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.bluetooth.BluetoothRule;

import java.util.List;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class BluetoothReceiver extends BroadcastReceiver {

    public static final String TAG = BluetoothReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        List<Rule> rules = new RulesDataSource(context).getRulesByCategory(BluetoothRule.TAG);
        MainActivity.doCheckAutoTasks(context, intent, rules);
    }
}
