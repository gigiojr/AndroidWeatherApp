package br.com.weatherapp.ui.fragment.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.weatherapp.R;
import br.com.weatherapp.databinding.FragmentHomeBinding;
import br.com.weatherapp.model.City;
import br.com.weatherapp.model.CityDb;
import br.com.weatherapp.util.Dialog;
import br.com.weatherapp.util.FragmentListener;
import br.com.weatherapp.ui.card.CityCard;
import br.com.weatherapp.ui.adapter.recycler.cityList.CityRecyclerAdapter;
import br.com.weatherapp.ui.component.FieldButtonComponent;
import br.com.weatherapp.ui.fragment.city.CityFragment;

public class HomeFragmentViewModel extends ViewModel
        implements FieldButtonComponent.FieldButtonComponentListener, CityCard.CityCardListener {

    private Context context;
    private FragmentListener listener;
    private FragmentHomeBinding binding;

    private List<CityCard> cityList;
    private FieldButtonComponent field;

    private RecyclerView recyclerView;
    private CityRecyclerAdapter recyclerViewAdapter;

    public HomeFragmentViewModel(Context context, FragmentListener listener){
        this.context = context;
        this.listener = listener;
        this.field = new FieldButtonComponent(this);
        this.getFavorites();
    }

    public void getFavorites(){
        this.cityList = new ArrayList<>();
        CityDb db = new CityDb(this.context);
        List<City> list = db.getList();
        for (City city : list) {
            this.cityList.add(new CityCard(city, this));
        }
        db.close();
    }

    public void updateData() {
        this.getFavorites();
        this.setRecyclerView(this.cityList);
    }

    public void setRecyclerView(List<CityCard> cityList) {
        this.recyclerView = this.binding.recyclerView;
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        this.recyclerViewAdapter = new CityRecyclerAdapter(cityList);
        this.recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void setBiding(FragmentHomeBinding binding) {
        this.binding = binding;
        this.binding.setViewModel(this.field);
        this.setRecyclerView(this.cityList);
    }

    @Override
    public void onFieldChange(String value) {
        List<CityCard> list;
        if(value.isEmpty()){
            list = this.cityList;
        } else{
            list = new ArrayList<>();
            for (CityCard cityCard : this.cityList){
                if(cityCard.city.name.contains(value)){
                    list.add(cityCard);
                }
            }
        }
        this.setRecyclerView(list);
    }

    @Override
    public void onFieldButtonClick(String value) {
        CityFragment fragment = CityFragment.newInstance(value);
        this.listener.onUpdateFragment(fragment);
    }

    @Override
    public void onCityClick(CityCard card) {
        CityFragment fragment = CityFragment.newInstance(card.city.name);
        this.listener.onUpdateFragment(fragment);
    }

    @Override
    public void onDeleteClick(final CityCard card) {
        AlertDialog.Builder dialog = Dialog.createAlertDialog(this.context,
                this.context.getResources().getString(R.string.confirm_delete_title),
                this.context.getResources().getString(R.string.confirm_delete_text));

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                CityDb db = new CityDb(context);
                db.delete(card.city._id);
                db.close();
                updateData();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}