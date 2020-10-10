package br.com.weatherapp.ui.adapter.recycler.cityWeather;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.weatherapp.databinding.CardWeatherBinding;
import br.com.weatherapp.ui.card.WeatherCard;

public class CityWeatherRecyclerAdapter extends RecyclerView.Adapter<CityWeatherViewHolder> {
    private List<WeatherCard> list;

    public CityWeatherRecyclerAdapter(List<WeatherCard> list) {
        this.list = list;
    }

    @Override
    public CityWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardWeatherBinding binding = CardWeatherBinding
                .inflate(layoutInflater, parent, false);
        return new CityWeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CityWeatherViewHolder holder, int position) {
        WeatherCard card = list.get(position);
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
