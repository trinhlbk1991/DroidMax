package com.icetea09.droidmax.rules.weatherforecast;

import com.icetea09.droidmax.rules.IRule;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public class WeatherForecastRule implements IRule {

    public static final String TAG = WeatherForecastRule.class.getSimpleName();

    protected double mCurrentLong;
    protected double mCurrentLat;
    protected String mWeatherValue;
    protected String mTriggerTime;

    public WeatherForecastRule(String mWeatherValue, String mTriggerTime) {
        this.mWeatherValue = mWeatherValue;
        this.mTriggerTime = mTriggerTime;
    }

    public void setCurrentLocation(double lon, double lat) {
        this.mCurrentLong = lon;
        this.mCurrentLat = lat;
    }

    @Override
    public boolean isSatisfied() {
        return false;
    }

    @Override
    public String convertToString() {
        return TAG;
    }
}
