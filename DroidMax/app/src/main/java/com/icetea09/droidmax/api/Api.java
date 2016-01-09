package com.icetea09.droidmax.api;

import com.icetea09.droidmax.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Api implements IApi {
    public static final String BASE_URL = BuildConfig.WEATHER_FORECAST_API_URL;

    private static IApi mInstance = null;
    private static WeatherService mWeatherService;

    public static IApi getInstance() {
        if (mInstance == null) {
            OkHttpClient client = new OkHttpClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            mWeatherService = retrofit.create(WeatherService.class);
            mInstance = new Api();
        }
        return mInstance;
    }

    @Override
    public WeatherService getWeatherService() {
        return mWeatherService;
    }
}
