package com.voitenko.checkweather.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.voitenko.checkweather.Adapters.AdapterWeather;
import com.voitenko.checkweather.Data.AllWeather;
import com.voitenko.checkweather.Data.Marker;
import com.voitenko.checkweather.Providers.Net.WeatherApi;
import com.voitenko.checkweather.Providers.Bd.MarkersProvider;
import com.voitenko.checkweather.Providers.Bd.WeatherProvider;
import com.voitenko.checkweather.R;
import com.voitenko.checkweather.Settings.ConstantsCW;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerWeather;
    private AdapterWeather adapterWeather;

    private WeatherApi.ApiInterface apiInterface;
    private Marker marker;
    private WeatherProvider weatherProvider;
    private Long idPosition;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        findViewById(R.id.updateWeatherBtn).setOnClickListener(this);
        recyclerWeather = findViewById(R.id.recyclerWeather);
        weatherProvider = new WeatherProvider(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idPosition = bundle.getLong(ConstantsCW.ID_PLACE_INTENT, -1);
        apiInterface = WeatherApi.getClient().create(WeatherApi.ApiInterface.class);

        MarkersProvider markersProvider = new MarkersProvider(this);
        marker = markersProvider.getItemById(idPosition);

//        ArrayList<AllWeather> weathersList = weatherProvider.getItemsByIdMarker(idPosition);
        recyclerWeather.setLayoutManager(new LinearLayoutManager(this));
        adapterWeather = new AdapterWeather(weatherProvider.getItemsByIdMarker(idPosition));
        recyclerWeather.setAdapter(adapterWeather);
        adapterWeather.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        getWeather(marker.getLatitude(), marker.getLongitude(), weatherProvider);
    }

    public void getWeather(Double lat, Double lng, final WeatherProvider weatherProvider) {
        String units = "metric";
        String key = ConstantsCW.API_WEATHER_KEY;

        Call<AllWeather> call = apiInterface.getWeather(String.valueOf(lat), String.valueOf(lng), units, key);
        call.enqueue(new Callback<AllWeather>() {
            @Override
            public void onResponse(Call<AllWeather> call, Response<AllWeather> response) {
              AllWeather allWeather = response.body();
              allWeather.setIdMarker(idPosition);
                Date currentTime = Calendar.getInstance().getTime();
              allWeather.setTime(currentTime.toString());
                weatherProvider.insetrItem(allWeather);
                adapterWeather = new AdapterWeather(weatherProvider.getItemsByIdMarker(idPosition));
                recyclerWeather.setAdapter(adapterWeather);
                adapterWeather.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AllWeather> call, Throwable t) {

            }
        });
    }
}