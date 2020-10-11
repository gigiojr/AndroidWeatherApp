package br.com.weatherapp.ui.fragment.city;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.model.LatLng;

import br.com.weatherapp.databinding.FragmentCityBinding;
import br.com.weatherapp.model.City;

/**
 * A placeholder fragment containing a simple view.
 */
public class CityFragment extends Fragment {

    private static final String ARG_TYPE = "type";
    private static final String ARG_CITY = "city";
    private static final String ARG_CITY_NAME = "city_name";
    private static final String ARG_CITY_LATLNG = "city_latlng";

    private CityFragmentViewModel viewModel;

    public static CityFragment newInstance(String cityName) {
        CityFragment fragment = new CityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TYPE, 0);
        bundle.putString(ARG_CITY_NAME, cityName);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static CityFragment newInstance(City city) {
        CityFragment fragment = new CityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TYPE, 1);
        bundle.putParcelable(ARG_CITY, city);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static CityFragment newInstance(LatLng latLng) {
        CityFragment fragment = new CityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TYPE, 2);
        bundle.putParcelable(ARG_CITY_LATLNG, latLng);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(ARG_TYPE)) {
            int type = bundle.getInt(ARG_TYPE);
            switch (type){
                case 0:
                    String cityName = getArguments().getString(ARG_CITY_NAME);
                    this.viewModel = new CityFragmentViewModel(this.getContext(),
                            cityName, getActivity().getSupportFragmentManager());
                    break;
                case 1:
                    City city = bundle.getParcelable(ARG_CITY);
                    this.viewModel = new CityFragmentViewModel(this.getContext(),
                            city, getActivity().getSupportFragmentManager());
                    break;
                case 2:
                    LatLng latLng = bundle.getParcelable(ARG_CITY_LATLNG);
                    this.viewModel = new CityFragmentViewModel(this.getContext(),
                            latLng, getActivity().getSupportFragmentManager());
                    break;
            }
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        FragmentCityBinding binding = FragmentCityBinding.inflate(inflater, container, false);
        this.viewModel.setBinding(binding);

        return binding.getRoot();
    }
}