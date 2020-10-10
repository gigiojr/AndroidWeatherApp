package br.com.weatherapp.ui.activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.View;

import br.com.weatherapp.R;
import br.com.weatherapp.util.FragmentListener;

public class HomeActivity extends AppCompatActivity implements FragmentListener {

    private HomeActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        ViewPager viewPager = findViewById(R.id.view_pager);
        this.viewModel = new HomeActivityViewModel(this, getSupportFragmentManager());

//        viewPager.setCurrentItem(1);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onUpdateFragment(Fragment fragment) {
        this.viewModel.updateFragment(fragment);
    }
}