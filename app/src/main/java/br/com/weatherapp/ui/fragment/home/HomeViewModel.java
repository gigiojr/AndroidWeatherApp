package br.com.weatherapp.ui.fragment.home;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.weatherapp.ui.card.CityCard;
import br.com.weatherapp.ui.recycler.CityRecyclerAdapter;

public class HomeViewModel extends ViewModel {

    private List<CityCard> cityList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public HomeViewModel(){
        this.cityList = new ArrayList<>();

        cityList.add(new CityCard("Cidade 1"));
        cityList.add(new CityCard("Cidade 2"));
        cityList.add(new CityCard("Cidade 3"));
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(Context context, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewAdapter = new CityRecyclerAdapter(this.cityList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}