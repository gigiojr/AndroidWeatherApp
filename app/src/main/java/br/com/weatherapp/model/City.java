package br.com.weatherapp.model;

public class City {
    public long _id;
    public String name;
    public Double latitude;
    public Double longitude;

    public City(long id, String name, Double latitude, Double longitude){
        this._id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(String name, Double latitude, Double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
