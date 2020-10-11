package br.com.weatherapp.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import br.com.weatherapp.R;
import br.com.weatherapp.ui.fragment.help.HelpFragment;
import br.com.weatherapp.ui.fragment.settings.SettingsFragment;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                this.onUpdateFragment(new SettingsFragment());
                break;
            case R.id.menu_help:
                this.onUpdateFragment(new HelpFragment());
                break;
        }
        return true;
    }

    @Override
    public void onUpdateFragment(Fragment fragment) {
        this.viewModel.updateFragment(fragment);
    }
}