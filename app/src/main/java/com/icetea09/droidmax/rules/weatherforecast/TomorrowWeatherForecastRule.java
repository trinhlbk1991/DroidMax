package com.icetea09.droidmax.rules.weatherforecast;

import android.util.Log;

import com.icetea09.droidmax.api.Api;
import com.icetea09.droidmax.api.WeatherService;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.model.WeatherForecast;
import com.icetea09.droidmax.utils.Constants;

import java.io.IOException;

/**
 * Created by KhanhTrinh on 1/9/2016.
 * MUST RUN ON BACKGROUND THREAD
 */
public class TomorrowWeatherForecastRule extends WeatherForecastRule {

    public static final String TAG = TomorrowWeatherForecastRule.class.getName();

    public TomorrowWeatherForecastRule(String mWeatherValue, String mTriggerTime) {
        super(mWeatherValue, mTriggerTime);
    }

    @Override
    public boolean isSatisfied() {
        if (mCurrentLong == 0 && mCurrentLat == 0) {
            Log.e(TAG, "Must set current location.");
            return false;
        }

        WeatherService weatherService = Api.getInstance().getWeatherService();
        retrofit.Call<WeatherForecast> call = weatherService.getWeatherForecastByLocation(String.valueOf(mCurrentLat),
                String.valueOf(mCurrentLong), Constants.WEATHER_FORECAST_API_KEY);
        WeatherForecast weatherForecast = null;
        try {
            weatherForecast = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherForecast != null && weatherForecast.list != null && weatherForecast.list.size() > 0
                && weatherForecast.list.get(1).weather.get(0).main.equalsIgnoreCase(mWeatherValue);
    }

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG)
                .append(Rule.ARGS_SEPARATOR)
                .append(mWeatherValue)
                .append(Rule.ARGS_SEPARATOR)
                .append(mTriggerTime);
        return stringBuilder.toString();
    }
}
