package br.com.weatherapp.ui.card;

import android.view.View;

/**
 * ViewModel class
 * Layout: layout/card_city.xml
 */
public class CityCard {

    public String city;
    public CityCardListener listener;

    public CityCard(String city, CityCardListener listener){
        this.city = city;
        this.listener = listener;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void onCityClick(View v){
        this.listener.onCityClick(this);
    }

    public void onDeleteClick(View v){
        this.listener.onDeleteClick(this);
    }

    public interface CityCardListener{
        void onCityClick(CityCard card);
        void onDeleteClick(CityCard card);
    }
}
