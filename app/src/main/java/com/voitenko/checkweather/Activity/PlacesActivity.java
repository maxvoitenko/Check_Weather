package com.voitenko.checkweather.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.voitenko.checkweather.Adapters.AdapterPlaces;
import com.voitenko.checkweather.Data.Marker;
import com.voitenko.checkweather.Interface.OnPositionClickListener;
import com.voitenko.checkweather.Providers.Bd.MarkersProvider;
import com.voitenko.checkweather.R;
import com.voitenko.checkweather.Settings.ConstantsCW;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlacesActivity extends AppCompatActivity implements View.OnClickListener, OnPositionClickListener{

    private RecyclerView recyclerPlace;
    private AdapterPlaces adapterPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        findViewById(R.id.addPlaceBtn).setOnClickListener(this);
        recyclerPlace = findViewById(R.id.recyclerPlace);
        recyclerPlace.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(PlacesActivity.this, MapActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Marker> places = new MarkersProvider(this).getAllMarkers();
        adapterPlaces=  new AdapterPlaces(places, this);
        adapterPlaces.setOnPositionClickListener(this);
        recyclerPlace.setAdapter(adapterPlaces);
        adapterPlaces.notifyDataSetChanged();
    }

    @Override
    public void onPositionClickListener(long id) {
        Intent intent = new Intent(PlacesActivity.this, WeatherActivity.class);
        intent.putExtra(ConstantsCW.ID_PLACE_INTENT, id);
        startActivity(intent);
    }
}
