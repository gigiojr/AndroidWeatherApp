package br.com.weatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
