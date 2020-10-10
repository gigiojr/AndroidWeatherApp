package br.com.weatherapp.ui.card;

import android.view.View;

import br.com.weatherapp.model.City;

/**
 * ViewModel class
 * Layout: layout/card_city.xml
 */
public class CityCard {

    public City city;
    public CityCardListener listener;

    public CityCard(City city, CityCardListener listener){
        this.city = city;
        this.listener = listener;
    }

    public CityCard(String city, CityCardListener listener){
        this.city = new City(city, null, null);
        this.listener = listener;
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
