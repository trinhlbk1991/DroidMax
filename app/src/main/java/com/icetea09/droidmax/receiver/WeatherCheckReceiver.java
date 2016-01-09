package com.icetea09.droidmax.receiver;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
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
import com.icetea09.droidmax.rules.weatherforecast.WeatherForecastRule;

import java.util.Calendar;
import java.util.List;

/**
 * Created by KhanhTrinh on 1/10/2016.
 */
public class WeatherCheckReceiver extends BroadcastReceiver {

    public static final String TAG = WeatherCheckReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");

        List<Rule> rules = new RulesDataSource(context).getRulesByCategory(WeatherForecastRule.TAG);

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

    public static void startAlarmManager(Context context) {
        Calendar calendar = Calendar.getInstance();

        /*calendar.set(Calendar.HOUR_OF_DAY, 3); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, 21);
        calendar.set(Calendar.SECOND, 0);*/
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + 10);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("WEATHER_CHECK_SERVICE");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pi);
    }
}
