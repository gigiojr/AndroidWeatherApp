package br.com.weatherapp.ui.fragment.city;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import br.com.openweathermapapi.OpenWeatherMap;
import br.com.openweathermapapi.model.ForecastModel;
import br.com.openweathermapapi.model.WeatherModel;
import br.com.weatherapp.R;
import br.com.weatherapp.databinding.FragmentCityBinding;
import br.com.weatherapp.model.City;
import br.com.weatherapp.model.CityDb;
import br.com.weatherapp.util.Dialog;
import br.com.weatherapp.ui.adapter.recycler.cityWeather.CityWeatherRecyclerAdapter;
import br.com.weatherapp.ui.card.WeatherCard;
import br.com.weatherapp.util.UserPreferences;

public class CityFragmentViewModel extends ViewModel
        implements OpenWeatherMap.RequestCallback, View.OnClickListener {

    private FragmentManager fragmentManager;
    private Context context;
    private String cityName;
    private City city;

    private FragmentCityBinding binding;
    private WeatherCard weatherCard;
    private List<WeatherCard> weatherList;
    private RecyclerView recyclerView;

    public CityFragmentViewModel(Context context, String cityName, FragmentManager fragmentManager){
        this.context = context;
        this.cityName = cityName;
        this.fragmentManager = fragmentManager;
        this.weatherCard = new WeatherCard();

        this.requestData();
    }

    public CityFragmentViewModel(Context context, City city, FragmentManager fragmentManager){
        this.context = context;
        this.city = city;
        this.cityName = city.name;
        this.fragmentManager = fragmentManager;
        this.weatherCard = new WeatherCard();

        this.requestData();
    }

    public CityFragmentViewModel(Context context, LatLng latLng, FragmentManager fragmentManager){
        this.context = context;
        this.weatherCard = new WeatherCard();
        this.fragmentManager = fragmentManager;

        this.requestData(latLng);
    }

    private void requestData(){
        String unity = UserPreferences.getPreference(this.context, UserPreferences.USER_UNITY);
        String lang = UserPreferences.getPreference(this.context, UserPreferences.USER_LANGUAGE);

        OpenWeatherMap API = new OpenWeatherMap(unity, lang);
        API.requestCurrentWeatherByCityName(context, cityName, this);
        API.requestForecastWeatherByCityName(context, cityName, this);
    }

    private void requestData(LatLng latLng){
        String unity = UserPreferences.getPreference(this.context, UserPreferences.USER_UNITY);
        String lang = UserPreferences.getPreference(this.context, UserPreferences.USER_LANGUAGE);

        String lat = String.valueOf(latLng.latitude);
        String lng = String.valueOf(latLng.longitude);
        OpenWeatherMap API = new OpenWeatherMap(unity, lang);
        API.requestCurrentWeatherByLatLng(context, lat, lng, this);
        API.requestForecastWeatherByLatLng(context, lat, lng, this);
    }

    public void setBinding(FragmentCityBinding binding) {
        this.binding = binding;
        this.binding.setTitle("Current Weather in " + this.cityName);
        this.binding.setViewModel(this.weatherCard);
        this.binding.setOnFavoriteClick(this);
        this.binding.setFavorite(this.city != null);
        this.setRecyclerView();
    }

    public void setRecyclerView() {
        this.weatherList = new ArrayList<>();
        this.recyclerView = this.binding.recyclerView;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        CityWeatherRecyclerAdapter recyclerViewAdapter = new CityWeatherRecyclerAdapter(this.weatherList);
        recyclerView.setAdapter(recyclerViewAdapter);

        this.binding.progressBar.setVisibility(View.VISIBLE);
        this.binding.executePendingBindings();
    }

    @Override
    public void requestSuccess(WeatherModel weatherModel) {
        if(this.cityName == null || this.cityName.isEmpty())
            this.cityName = weatherModel.cityName;
        this.weatherCard = new WeatherCard(weatherModel);
        this.binding.setTitle("Current Weather in " + this.cityName);
        this.binding.setViewModel(this.weatherCard);
    }

    @Override
    public void requestSuccess(ForecastModel forecastModel) {
        for (WeatherModel weatherModel : forecastModel.weatherModelList) {
            this.weatherList.add(new WeatherCard(weatherModel));
        }
        CityWeatherRecyclerAdapter recyclerViewAdapter = new CityWeatherRecyclerAdapter(this.weatherList);
        this.recyclerView.setAdapter(recyclerViewAdapter);

        int visibility = this.weatherList.size() > 0 ? View.GONE : View.VISIBLE;
        this.binding.progressBar.setVisibility(visibility);
    }

    @Override
    public void requestError() {
        Dialog.showAlert(this.context,
                this.context.getString(R.string.error_connect_title),
                this.context.getString(R.string.error_connect_text));
        this.fragmentManager.popBackStack();
    }

    @Override
    public void onClick(View v) {
        if(city != null){
            this.deleteCity();
        } else{
            this.insertCity();
        }
    }

    private void insertCity(){
        City city = new City(this.cityName, weatherCard.latitude, weatherCard.longitude);
        CityDb db = new CityDb(this.context);
        long id = db.insert(city);
        if(id > 0){
            city._id = id;
            this.city = city;
            this.binding.setFavorite(true);
            this.binding.executePendingBindings();
        }
        db.close();
    }

    private void deleteCity(){
        CityDb db = new CityDb(this.context);
        db.delete(city._id);
        db.close();
        this.city = null;
        this.binding.setFavorite(false);
        this.binding.executePendingBindings();
    }
}