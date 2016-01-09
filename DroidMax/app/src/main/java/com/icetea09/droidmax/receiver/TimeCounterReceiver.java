package com.icetea09.droidmax.receiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


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

public class TimeCounterReceiver extends BroadcastReceiver {

	private static final String TAG = TimeCounterReceiver.class.getSimpleName();
	private static final int TIME_COUNTER = 1000 * 5 * 1; //milisecond * second * minute

	Calendar calendar;
	int currentHour;

	Context context;

	private LocationManager mLocationManager = null;
//	private static final int LOCATION_INTERVAL = 3600000;
//	private static final float LOCATION_DISTANCE = 5000f;

	Location currentLocation;


//	LocationListener[] mLocationListeners = new LocationListener[] {
//			new LocationListener(LocationManager.GPS_PROVIDER),
//			new LocationListener(LocationManager.NETWORK_PROVIDER)
//	};

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		this.context = context;
		calendar = Calendar.getInstance();
		currentHour = calendar.getTime().getHours();
//		Log.d(TAG, "Calendar hour: " + calendar.getTime().getHours());
		updateLocation(context);
	}

	@SuppressWarnings("unchecked")
	public void updateLocation(final Context context) {
		this.context = context;
		initializeLocationManager();

//		updateGPSLocation();

		if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            else{
                currentLocation = mLocationManager.getLastKnownLocation("network");
			if (currentLocation == null) {
				Log.d(TAG, "Current Location == null");
			}
			else{//Location is not null
				Log.d(TAG, "Current Location != null");
				Log.d(TAG, "Long: " + currentLocation.getLongitude() + " ----Lat: " + currentLocation.getLatitude());

//				if(isLocationChange(currentLocation)){//Calculator Distance
//					Log.d(TAG, "isLocationChange true");
//				}
//				else{
//					Log.d(TAG, "isLocationChange false");
//				}
			}
		}
	}


	public void setAlarm(Context context) {
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, TimeCounterReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), TIME_COUNTER, pi);
	}

	public void cancelAlarm(Context context) {
		Intent intent = new Intent(context, TimeCounterReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
	}

	private void initializeLocationManager() {
		Log.d(TAG, "initializeLocationManager");
		if (mLocationManager == null) {
			mLocationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		}
	}


//	private boolean isLocationChange(Location currentLocation){
		//Get local Location
//		Location offlineLocation = new Location("network");
//		offlineLocation.setLongitude(Double.parseDouble(Const.getOfflineLong(context)));
//		offlineLocation.setLatitude(Double.parseDouble(Const.getOfflineLat(context)));
//
//		//Distance between new location and localLocation
//		float distanceMeters = currentLocation.distanceTo(offlineLocation);
//		Log.d(TAG, "isLocationChange - distanceMeters: " + distanceMeters);
//
//		if(distanceMeters >= 1000)
//			return true;
//
//		return false;
//	}
}