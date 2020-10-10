package br.com.weatherapp.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import br.com.weatherapp.R;
import br.com.weatherapp.util.FragmentListener;

public class HomeActivity extends AppCompatActivity implements FragmentListener {

    private HomeActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.viewModel = new HomeActivityViewModel(this, getSupportFragmentManager());
    }

    @Override
    public void onUpdateFragment(Fragment fragment) {
        this.viewModel.updateFragment(fragment);
    }
}