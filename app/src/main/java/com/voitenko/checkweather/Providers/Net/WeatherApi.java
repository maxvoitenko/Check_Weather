package com.voitenko.checkweather.Providers.Net;

import com.voitenko.checkweather.Data.AllWeather;
import com.voitenko.checkweather.Settings.ConstantsCW;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherApi {
    private static Retrofit retrofit = null;
    public interface ApiInterface {
        @GET("weather")
        Call<AllWeather> getWeather(
                @Query("lat") String lat,
                @Query("lon") String lon,
                @Query("units") String units,
                @Query("appid") String appid);
    }
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ConstantsCW.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}