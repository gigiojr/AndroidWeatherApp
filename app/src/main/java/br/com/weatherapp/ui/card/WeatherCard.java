package br.com.weatherapp.ui.card;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.openweathermapapi.model.WeatherModel;
import br.com.weatherapp.BR;

/**
 * ViewModel class
 * Layout: layout/card_weather.xml
 */
public class WeatherCard extends BaseObservable {

    public Boolean favorite;

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

    private WeatherCardListener listener;

    public WeatherCard(WeatherCardListener listener){
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
        this.favorite = false;
        this.listener = listener;
    }

    public WeatherCard(WeatherModel weatherModel, WeatherCardListener listener){
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
        this.latitude = weatherModel.latitude;
        this.longitude = weatherModel.longitude;
        this.favorite = false;
        this.listener = listener;
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

    public void onFavoriteClick(View v){
        this.setFavorite(!this.favorite);
        this.listener.onFavoriteClick(this);
    }

    @Bindable
    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
        notifyPropertyChanged(BR.favorite);
    }

    public interface WeatherCardListener{
        void onFavoriteClick(WeatherCard card);
    }
}
