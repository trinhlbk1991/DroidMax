package com.icetea09.droidmax.rules.location;

import android.content.Context;
import android.location.Location;

import com.icetea09.droidmax.rules.IRule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class LocationRules implements IRule{
    String strLocation;
    Context context;
    Location newLocation;
    Location userCurrentLocation;

    @Override
    public boolean isSatisfied() {
        return false;
    }

//    public void setContext(Context context){
//        this.context = context;
//    }
//
    public void setUserCurrentLocation(Location userCurrentLocation){
        this.userCurrentLocation = userCurrentLocation;
    }

    public LocationRules(String strLocation, String longitude, String latitude){
        this.strLocation = strLocation;
        newLocation = new Location("network");
        newLocation.setLongitude(Double.parseDouble(longitude));
        newLocation.setLatitude(Double.parseDouble(latitude));

    }

    @Override
    public String convertToString() {
        return null;
    }
}
