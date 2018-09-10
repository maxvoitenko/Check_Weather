package com.voitenko.checkweather.Activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.voitenko.checkweather.Data.Marker;
import com.voitenko.checkweather.Providers.Bd.MarkersProvider;
import com.voitenko.checkweather.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener {

    private GoogleMap mMap;
    private LatLng positionLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewById(R.id.okayBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mMap = googleMap;
                mMap.clear();
                mMap.setOnMapClickListener(MapActivity.this);

                if(ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1020);
                }else{
                    mMap.setMyLocationEnabled(true);
                }
                mMap.setTrafficEnabled(true);
                mMap.setBuildingsEnabled(true);
                UiSettings uiSettings = mMap.getUiSettings();
                uiSettings.setZoomControlsEnabled(true);
                uiSettings.setCompassEnabled(true);
                uiSettings.setMyLocationButtonEnabled(true);
                uiSettings.setScrollGesturesEnabled(true);
                uiSettings.setZoomGesturesEnabled(true);
                LatLng myLocation = myLocation();
                if(myLocation!=null){
                    mMap.addMarker(new MarkerOptions().position(myLocation).title("I'm here"));
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                    mMap.animateCamera(zoom);
                }

            }
        }).run();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.clear();
        positionLatLng= latLng;
        MarkerOptions newMarker = new MarkerOptions().position(
                positionLatLng)
                .title("new Place");
        newMarker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(newMarker);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.okayBtn:
                if(positionLatLng!=null) {
                    MarkersProvider markersProvider = new MarkersProvider(this);
                    try {
                        markersProvider.insetrItem(new Marker(positionLatLng.latitude, positionLatLng.longitude
                                                ,getCity(positionLatLng.latitude, positionLatLng.longitude) ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        onBackPressed();
                    }
                }break;
            case R.id.cancelBtn:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LatLng myLocation = myLocation();
//                    mMap.addMarker(new MarkerOptions().position(new LatLng(15d, 15d)).title("I'm here"));
                    mMap.addMarker(new MarkerOptions().position(myLocation).title("I'm here"));
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                    mMap.animateCamera(zoom);
                }else{
                    Toast.makeText(this, "Необходимо подтвердить разрешение для определения гео-локации", Toast.LENGTH_SHORT).show();
                }
    }

    private LatLng myLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1020);
        }else {
            LocationManager locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            Location location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            positionLatLng = new LatLng(latitude,longitude);
            return positionLatLng;
        }return null;
    }
   private String getCity(Double lat, Double lng) throws IOException {
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
        if (addresses.size() > 0) {
            return addresses.get(0).getLocality();
        } else {
            return "";
        }
    }
}
