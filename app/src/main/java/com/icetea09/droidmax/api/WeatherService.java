package com.icetea09.droidmax.api;

import com.icetea09.droidmax.model.WeatherForecast;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by KhanhTrinh on 1/9/2016.
 */
public interface WeatherService {

    @GET("forecast")
    Call<WeatherForecast> getWeatherForecastByCityId(@Query("id") String citiId, @Query("appid") String apiKey);

    @GET("forecast")
    Call<WeatherForecast> getWeatherForecastByLocation(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String apiKey);

}
