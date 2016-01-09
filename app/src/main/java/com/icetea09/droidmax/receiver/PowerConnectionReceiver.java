package com.icetea09.droidmax.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.icetea09.droidmax.MainActivity;
import com.icetea09.droidmax.database.RulesDataSource;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.battery.BatteryRule;

import java.util.List;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class PowerConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        List<Rule> rules = new RulesDataSource(context).getRulesByCategory(BatteryRule.TAG);
        MainActivity.doCheckAutoTasks(context, intent, rules);
    }
}
