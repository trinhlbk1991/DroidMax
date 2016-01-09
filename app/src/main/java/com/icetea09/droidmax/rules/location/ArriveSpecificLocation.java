package com.icetea09.droidmax.rules.location;

import com.icetea09.droidmax.model.Rule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class ArriveSpecificLocation extends LocationRule {

    public static final String TAG = ArriveSpecificLocation.class.getName();

    @Override
    public boolean isSatisfied() {
        return isExitSpecialLocation();
    }

    public ArriveSpecificLocation(String strLocation, String longitude, String latitude) {
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
        return mCurrentLocation.distanceTo(mDestination) >= 500;
    }

    @Override
    public String getRuleDescription() {
        return "Arrive " + mLocationName;
    }
}
