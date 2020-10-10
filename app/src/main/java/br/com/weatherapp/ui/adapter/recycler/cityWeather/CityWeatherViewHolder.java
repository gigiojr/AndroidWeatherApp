package br.com.weatherapp.ui.adapter.recycler.cityWeather;

import androidx.recyclerview.widget.RecyclerView;

import br.com.weatherapp.databinding.CardWeatherBinding;
import br.com.weatherapp.ui.card.WeatherCard;

public class CityWeatherViewHolder extends RecyclerView.ViewHolder {

    private CardWeatherBinding binding;

    public CityWeatherViewHolder(CardWeatherBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(WeatherCard card) {
        binding.setViewModel(card);
        binding.executePendingBindings();
    }
}