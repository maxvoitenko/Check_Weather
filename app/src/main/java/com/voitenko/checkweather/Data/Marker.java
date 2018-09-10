package com.voitenko.checkweather.Data;

public class Marker {
    private long id;
    private double latitude;
    private double longitude;
    private String cityInMap;

    public Marker(long id, double latitude, double longitude, String cityInMap) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityInMap = cityInMap;
}
public Marker(double latitude, double longitude, String cityInMap){
    this(-1,latitude,longitude, cityInMap);
    }

    public String getCityInMap() {
        return cityInMap;
    }

    public void setCityInMap(String cityInMap) {
        this.cityInMap = cityInMap;
    }

    public void setId(long id) { this.id = id; }

    public long getId() { return id; }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
