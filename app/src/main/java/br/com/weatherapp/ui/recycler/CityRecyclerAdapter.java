package br.com.weatherapp.ui.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.weatherapp.databinding.CardCityBinding;
import br.com.weatherapp.ui.card.CityCard;

public class CityRecyclerAdapter extends RecyclerView.Adapter<CityViewHolder> {
    private List<CityCard> list;

    public CityRecyclerAdapter(List<CityCard> list) {
        this.list = list;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardCityBinding binding = CardCityBinding
                .inflate(layoutInflater, parent, false);
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        CityCard card = list.get(position);
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
