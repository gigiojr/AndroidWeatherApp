package br.com.weatherapp.ui.fragment.city;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import br.com.weatherapp.databinding.FragmentCityBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class CityFragment extends Fragment {

    private static final String ARG_CITY = "city_name";

    private CityFragmentViewModel viewModel;

    public static CityFragment newInstance(String cityName) {
        CityFragment fragment = new CityFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_CITY, cityName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String cityName = "";
        if (getArguments() != null) {
            cityName = getArguments().getString(ARG_CITY);
        }
        this.viewModel = new CityFragmentViewModel(this.getContext(), cityName);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        FragmentCityBinding binding = FragmentCityBinding.inflate(inflater, container, false);
        this.viewModel.setBiding(binding);

        return binding.getRoot();
    }
}