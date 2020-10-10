package br.com.weatherapp.ui.adapter.recycler.cityList;

import androidx.recyclerview.widget.RecyclerView;

import br.com.weatherapp.databinding.CardCityBinding;
import br.com.weatherapp.ui.card.CityCard;

public class CityViewHolder extends RecyclerView.ViewHolder {

    private CardCityBinding binding;

    public CityViewHolder(CardCityBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(CityCard card) {
        binding.setViewModel(card);
        binding.executePendingBindings();
    }
}