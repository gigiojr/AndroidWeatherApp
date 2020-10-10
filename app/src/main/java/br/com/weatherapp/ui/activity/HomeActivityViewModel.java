package br.com.weatherapp.ui.activity;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import br.com.weatherapp.R;
import br.com.weatherapp.ui.fragment.home.HomeFragment;

public class HomeActivityViewModel {

    private Context context;
    private FragmentManager fragmentManager;

    public HomeActivityViewModel(Context context, FragmentManager fm){
        this.context = context;
        this.fragmentManager = fm;

        this.fragmentManager.beginTransaction()
                .replace(R.id.frameLayout, HomeFragment.newInstance())
                .commitNow();
    }

    public void updateFragment(Fragment fragment){
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
