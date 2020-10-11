package br.com.weatherapp.ui.fragment.maps;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.weatherapp.model.City;
import br.com.weatherapp.ui.fragment.city.CityFragment;
import br.com.weatherapp.util.FragmentListener;

public class MapsFragmentViewModel extends ViewModel
        implements GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLongClickListener {

    private FragmentListener listener;
    private GoogleMap googleMap;
    private City city;

    public MapsFragmentViewModel(City city, FragmentListener listener){
        this.city = city;
        this.listener = listener;
    }

    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng position = new LatLng(city.latitude, city.longitude);
        this.googleMap.addMarker(new MarkerOptions().position(position).title(city.name));
        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setOnMapLongClickListener(this);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        CityFragment fragment = CityFragment.newInstance(this.city);
        this.listener.onUpdateFragment(fragment);
        return false;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        CityFragment fragment = CityFragment.newInstance(latLng);
        this.listener.onUpdateFragment(fragment);
    }
}
