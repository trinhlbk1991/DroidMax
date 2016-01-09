package com.icetea09.droidmax.rules.location;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class SpecialLocation extends LocationRules{
    @Override
    public boolean isSatisfied() {
        return isSpecialLocation();
    }

    public SpecialLocation(String strLocation, String longitude, String latitude) {
        super(strLocation, longitude, latitude);
    }

    @Override
    public String convertToString() {
        return null;
    }

    protected boolean isSpecialLocation(){
        if (userCurrentLocation.distanceTo(newLocation) >= 500){
            return true;
        }
        return false;
    }
}
