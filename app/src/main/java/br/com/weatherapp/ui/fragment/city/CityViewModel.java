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
import br.com.weatherapp.model.Dialog;
import br.com.weatherapp.ui.adapter.recycler.cityWeather.CityWeatherRecyclerAdapter;
import br.com.weatherapp.ui.card.WeatherCard;

public class CityViewModel extends ViewModel implements OpenWeatherMap.RequestCallback{

    private Context context;
    private String cityName;

    private FragmentCityBinding biding;
    private WeatherCard weatherCard;
    private List<WeatherCard> weatherList;
    private RecyclerView recyclerView;

    public CityViewModel(Context context, String cityName){
        this.context = context;
        this.cityName = cityName;
        this.weatherList = new ArrayList<>();
        this.weatherCard = new WeatherCard();

        OpenWeatherMap.requestCurrentWeatherByCityName(context, cityName, this);
        OpenWeatherMap.requestForecastWeatherByCityName(context, cityName, this);
    }

    public void setBiding(FragmentCityBinding biding) {
        this.biding = biding;
        this.biding.setTitle("Current Weather in " + this.cityName);
        this.biding.setViewModel(this.weatherCard);
        this.updateRecyclerView();
    }

    public void updateRecyclerView() {
        this.recyclerView = this.biding.recyclerView;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        CityWeatherRecyclerAdapter recyclerViewAdapter = new CityWeatherRecyclerAdapter(this.weatherList);
        recyclerView.setAdapter(recyclerViewAdapter);

        int visibility = this.weatherList.size() > 0 ? View.VISIBLE : View.GONE;
        this.biding.progressBar.setVisibility(visibility);
    }

    @Override
    public void requestSuccess(WeatherModel weatherModel) {
        this.weatherCard = new WeatherCard(weatherModel);
        this.biding.setViewModel(this.weatherCard);
    }

    @Override
    public void requestSuccess(ForecastModel forecastModel) {
        for (WeatherModel weatherModel : forecastModel.weatherModelList) {
            this.weatherList.add(new WeatherCard(weatherModel));
        }
        CityWeatherRecyclerAdapter recyclerViewAdapter = new CityWeatherRecyclerAdapter(this.weatherList);
        this.recyclerView.setAdapter(recyclerViewAdapter);

        int visibility = this.weatherList.size() > 0 ? View.GONE : View.VISIBLE;
        this.biding.progressBar.setVisibility(visibility);
    }

    @Override
    public void requestError() {
        Dialog.showAlert(this.context,
                this.context.getString(R.string.error_connect_title),
                this.context.getString(R.string.error_connect_text));
    }
}