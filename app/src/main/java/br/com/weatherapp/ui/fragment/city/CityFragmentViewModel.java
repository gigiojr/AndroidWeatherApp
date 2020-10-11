package br.com.weatherapp.ui.fragment.city;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class CityFragmentViewModel extends ViewModel
        implements OpenWeatherMap.RequestCallback, View.OnClickListener {

    private Context context;
    private String cityName;
    private City city;

    private FragmentCityBinding binding;
    private WeatherCard weatherCard;
    private List<WeatherCard> weatherList;
    private RecyclerView recyclerView;

    public CityFragmentViewModel(Context context, String cityName){
        this.context = context;
        this.cityName = cityName;
        this.weatherCard = new WeatherCard();

        OpenWeatherMap.requestCurrentWeatherByCityName(context, cityName, this);
        OpenWeatherMap.requestForecastWeatherByCityName(context, cityName, this);
    }

    public CityFragmentViewModel(Context context, City city){
        this.context = context;
        this.city = city;
        this.cityName = city.name;
        this.weatherCard = new WeatherCard();

        OpenWeatherMap.requestCurrentWeatherByCityName(context, cityName, this);
        OpenWeatherMap.requestForecastWeatherByCityName(context, cityName, this);
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
        this.weatherCard = new WeatherCard(weatherModel);
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