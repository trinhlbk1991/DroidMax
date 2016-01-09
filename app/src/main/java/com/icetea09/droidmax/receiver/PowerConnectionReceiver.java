package com.icetea09.droidmax.receiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.icetea09.droidmax.MainActivity;
import com.icetea09.droidmax.database.RulesDataSource;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.battery.BatteryRule;

import java.util.List;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class PowerConnectionReceiver extends BroadcastReceiver {

    public static final String TAG = PowerConnectionReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        List<Rule> rules = new RulesDataSource(context).getRulesByCategory(BatteryRule.TAG);

        if (rules.size() > 0) {
            Location currentLocation;
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                currentLocation = null;
            } else {
                LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                currentLocation = mLocationManager.getLastKnownLocation("network");
                if (currentLocation != null) {
                    Log.d(TAG, "Long: " + currentLocation.getLongitude() + " - Lat: " + currentLocation.getLatitude());
                }
            }

            MainActivity.doCheckAutoTasks(context, intent, currentLocation, rules);
        }

    }
}
