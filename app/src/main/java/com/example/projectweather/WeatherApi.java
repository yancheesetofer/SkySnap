package com.example.projectweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/2.5/weather?units=metric&")
    Call<ResponseWeather> getCurrentWeatherDataByCity(@Query("q") String city, @Query("APPID") String app_id);


    @GET("data/2.5/weather?units=metric&")
    Call<ResponseWeather> getCurrentWeatherDataByLocation(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

}