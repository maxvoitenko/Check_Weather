package com.voitenko.checkweather.Data;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllWeather {

    public AllWeather(long idWeath, long idMarker, Coord coord
            ,List<Weather> weather, String base, Main main
            , Clouds clouds, int dt, Sys sys
            , int id, String name, int cod) {
        this.idWeath = idWeath;
        this.idMarker = idMarker;
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }
    public AllWeather(long idMarker, String city, String country
            , String description, String main, String temp, String time){
        this.idMarker = idMarker;
        this.name=city;
        this.sys = new Sys(country);
        weather =  new ArrayList<>();
        weather.add(new Weather(description, main));
        this.main = new Main(temp);
        this.time = time;
    }

    @Expose(serialize = false, deserialize = false)
    private long idWeath;
    @Expose(serialize = false, deserialize = false)
    private long idMarker;
    @Expose(serialize = false, deserialize = false)
    private String time;


    @SerializedName("coord")
    private Coord coord;
    @SerializedName("weather")
    private List<Weather> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private Main main;
    //    @SerializedName("wind")
//    private Wind wind ;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("dt")
    private int dt;
    @SerializedName("sys")
    private Sys sys;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private int cod;

    public class Coord{
        public double message;
        public String country;
        public int sunrise;
        public int sunset;
    }

    public class Clouds {
        public int all;}

    public class Wind {
        public double speed;
        public int deg;}

    public class Main {
        public Main(String temp) {
            this.temp = Double.parseDouble(temp);
        }

        public double temp;
        public double pressure;
        public int humidity;
        public double temp_min;
        public double temp_max;
        public double sea_level;
        public double grnd_level;
    }
    public  String getMainTemp(){ return String.valueOf(main.temp); }
    public  void setMainTemp(String s){ main.temp = Double.parseDouble(s); }

    public class Weather {
        public Weather(String main, String description) {
            this.main = main;
            this.description = description;
        }

        public int id;
        public String main;
        public String description;
        public String icon;
    }
    public  String getWeatherMain(){ return String.valueOf(weather.get(0).main); }
    public  void setWeatherMain(String s){ weather.get(0).main = s; }
    public  String getWeatherDescription(){ return String.valueOf(weather.get(0).description); }
    public  void setWeatherDescription(String s){ weather.get(0).description = s; }
    public  String getWeatherId(){ return String.valueOf(weather.get(0).id); }
    public  String getWeatherIcon(){ return String.valueOf(weather.get(0).icon); }

    public class Sys {
        public Sys(String country) {
            this.country = country;
        }
        public double message;
        public String country;
        public int sunrise;
        public int sunset;
    }
    public  String getSysCountry(){ return sys.country; }
    public  void setSysCountry(String s){ sys.country = s; }
    public  String getSysMessage(){ return String.valueOf(sys.message); }
    public  String getSysSunrise(){ return String.valueOf(sys.sunrise); }
    public  String getSysSunset(){ return String.valueOf(sys.sunset); }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        return String.valueOf(dt);
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public long getIdMarker() {
        return idMarker;
    }

    public void setIdMarker(long idMarker) {
        this.idMarker = idMarker;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
