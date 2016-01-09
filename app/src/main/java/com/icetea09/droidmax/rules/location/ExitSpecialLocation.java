package com.icetea09.droidmax.rules.location;

import android.location.Location;
import android.util.Log;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class ExitSpecialLocation extends LocationRules{
    String TAG = ExitSpecialLocation.class.getName();
    @Override
    public boolean isSatisfied() {
        return isExitSpecialLocation();
    }

    public ExitSpecialLocation(String strLocation, String longitude, String latitude) {
        super(strLocation, longitude, latitude);
    }

    @Override
    public String convertToString() {
        return super.convertToString();
    }

    protected boolean isExitSpecialLocation(){
        Log.d(TAG, userCurrentLocation.distanceTo(newLocation) + "");
        if (userCurrentLocation.distanceTo(newLocation) >= 1000){
            return true;
        }
        return false;
    }

}
