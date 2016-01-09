package com.icetea09.droidmax.rules.location;

import android.location.Location;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.rules.IRule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class LocationRule implements IRule {

    public static final String TAG = LocationRule.class.getSimpleName();

    protected String mLocationName;
    protected Location mDestination;
    protected Location mCurrentLocation;

    @Override
    public boolean isSatisfied() {
        return false;
    }

    public void setCurrentLocation(Location mCurrentLocation) {
        this.mCurrentLocation = mCurrentLocation;
    }

    public LocationRule(String strLocation, String longitude, String latitude) {
        this.mLocationName = strLocation;
        mDestination = new Location("network");
        mDestination.setLongitude(Double.parseDouble(longitude));
        mDestination.setLatitude(Double.parseDouble(latitude));
    }

    @Override
    public String convertToString() {
        return TAG;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_location;
    }

    @Override
    public String getRuleDescription() {
        return TAG;
    }

    @Override
    public String getCategory() {
        return TAG;
    }
}
