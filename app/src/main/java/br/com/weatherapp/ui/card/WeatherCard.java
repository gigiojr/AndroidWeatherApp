package br.com.weatherapp.ui.card;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.openweathermapapi.model.WeatherModel;

/**
 * ViewModel class
 * Layout: layout/card_weather.xml
 */
public class WeatherCard extends BaseObservable {
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

    public Double latitude;
    public Double longitude;

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
        this.mainPressure = "Pressure: " + weatherModel.mainPressure + " hpa";
        this.windSpeed = "Wind: " + weatherModel.windSpeed + "m/s";
        this.cloudsAll = "Clouds: " + weatherModel.cloudsAll + "%";
        this.latitude = weatherModel.latitude;
        this.longitude = weatherModel.longitude;
    }

    @BindingAdapter("app:srcCompat")
    public static void bindSrcCompat(FloatingActionButton actionButton, Drawable drawable){
        actionButton.setImageDrawable(drawable);
    }

    public int getShowLoad(){
        return !(this.validFields(this.date) && this.validFields(this.weatherDescription) &&
                this.validFields(mainTemp)) ? View.VISIBLE : View.GONE;
    }

    public boolean validFields(String field){
        return field != null && !field.isEmpty();
    }
}
