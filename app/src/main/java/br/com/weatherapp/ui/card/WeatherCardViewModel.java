package br.com.weatherapp.ui.card;

import br.com.openweathermapapi.model.WeatherModel;

/**
 * ViewModel class
 * Layout: layout/card_weather.xml
 */
public class WeatherCardViewModel {

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

    public WeatherCardViewModel(WeatherModel weatherModel){
        this.date = weatherModel.getDateFormated();
        this.weatherIcon = weatherModel.getIconUrl();
        this.weatherDescription = weatherModel.weatherDescription;
        this.mainTemp = String.valueOf(weatherModel.mainTemp) + " °C";
        this.mainTempMin = "Min: " + String.valueOf(weatherModel.mainTempMin) + " °C";
        this.mainTempMax = "Max: " + String.valueOf(weatherModel.mainTempMax) + " °C";
        this.mainHumidity = "Humidade: " + String.valueOf(weatherModel.mainHumidity) + "%";
        this.mainPressure = "Pressão: " + String.valueOf(weatherModel.mainPressure) + " hpa";
        this.windSpeed = "Vento: " + String.valueOf(weatherModel.windSpeed) + "m/s";
        this.cloudsAll = "Núvens: " + String.valueOf(weatherModel.cloudsAll) + "%";
    }
}
