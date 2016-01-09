package com.icetea09.droidmax.rules.location;

import android.util.Log;

import com.icetea09.droidmax.model.Rule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class ExitSpecificLocation extends LocationRule {

    public static final String TAG = ExitSpecificLocation.class.getName();

    @Override
    public boolean isSatisfied() {
        return isExitSpecialLocation();
    }

    public ExitSpecificLocation(String strLocation, String longitude, String latitude) {
        super(strLocation, longitude, latitude);
    }

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG)
                .append(Rule.ARGS_SEPARATOR)
                .append(mLocationName)
                .append(Rule.ARGS_SEPARATOR)
                .append(mDestination.getLongitude())
                .append(Rule.ARGS_SEPARATOR)
                .append(mDestination.getLatitude());
        return stringBuilder.toString();
    }

    protected boolean isExitSpecialLocation() {
        if (mCurrentLocation == null) {
            return false;
        }
        Log.d(TAG, mCurrentLocation.distanceTo(mDestination) + "");
        if (mCurrentLocation.distanceTo(mDestination) >= 1000) {
            return true;
        }
        return false;
    }

    @Override
    public String getRuleDescription() {
        return "Exit " + mLocationName;
    }
}
