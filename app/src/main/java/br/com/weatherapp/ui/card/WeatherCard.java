package br.com.weatherapp.ui.card;

import android.view.View;

import br.com.openweathermapapi.model.WeatherModel;

/**
 * ViewModel class
 * Layout: layout/card_weather.xml
 */
public class WeatherCard {

    public String date;
    public String weatherIcon;
    public String weatherDescription;

    public String mainTemp;
    public String mainTempMin;
    public String mainTempMax;
    public String mainPressure;
    public String mainHumidity;

    public String windSpeed;
    public String cloudsAll;

    public WeatherCard(){
        this.date = "";
        this.weatherIcon = "";
        this.weatherDescription = "";
        this.mainTemp = "";
        this.mainTempMin = "";
        this.mainTempMax = "";
        this.mainHumidity = "";
        this.mainPressure = "";
        this.windSpeed = "";
        this.cloudsAll = "";
    }

    public WeatherCard(WeatherModel weatherModel){
        this.date = weatherModel.getDateFormatted();
        this.weatherIcon = weatherModel.getIconUrl();
        this.weatherDescription = weatherModel.weatherDescription;
        this.mainTemp =weatherModel.mainTemp + " °C";
        this.mainTempMin = "Min: " + weatherModel.mainTempMin + " °C";
        this.mainTempMax = "Max: " + weatherModel.mainTempMax + " °C";
        this.mainHumidity = "humidity: " + weatherModel.mainHumidity + "%";
        this.mainPressure = "Pressão: " + weatherModel.mainPressure + " hpa";
        this.windSpeed = "Wind: " + weatherModel.windSpeed + "m/s";
        this.cloudsAll = "Núvens: " + weatherModel.cloudsAll + "%";
    }

    public int getShowLoad(){
        return !(this.validFields(this.date) && this.validFields(this.weatherDescription) &&
                this.validFields(mainTemp)) ? View.VISIBLE : View.GONE;
    }

    public boolean validFields(String field){
        return field != null && !field.isEmpty();
    }
}
