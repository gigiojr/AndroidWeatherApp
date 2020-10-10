package br.com.weatherapp.ui.card;

/**
 * ViewModel class
 * Layout: layout/card_city.xml
 */
public class CityCard {

    public String city;

    public CityCard(String city){
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
