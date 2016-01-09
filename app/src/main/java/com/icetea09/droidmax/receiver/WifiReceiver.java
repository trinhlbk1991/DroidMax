package com.icetea09.droidmax.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.icetea09.droidmax.MainActivity;
import com.icetea09.droidmax.database.RulesDataSource;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.network.WifiRule;

import java.util.List;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiReceiver extends BroadcastReceiver {

    String TAG = WifiReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        List<Rule> rules = new RulesDataSource(context).getRulesByCategory(WifiRule.TAG);
        MainActivity.doCheckAutoTasks(context, intent, rules);
    }
}
