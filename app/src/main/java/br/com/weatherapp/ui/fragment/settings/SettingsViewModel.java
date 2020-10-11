package br.com.weatherapp.ui.fragment.settings;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import br.com.weatherapp.databinding.FragmentSettingsBinding;
import br.com.weatherapp.util.UserPreferences;

public class SettingsViewModel extends ViewModel implements View.OnClickListener{

    private Context context;
    private SettingForm form;
    private FragmentManager fragmentManager;
    private FragmentSettingsBinding binding;

    public SettingsViewModel(Context context, FragmentManager fm){
        this.context = context;
        this.fragmentManager = fm;
        String unity = UserPreferences.getPreference(context, UserPreferences.USER_UNITY);
        String lang = UserPreferences.getPreference(context, UserPreferences.USER_LANGUAGE);

        this.form = new SettingForm(unity, lang);
    }

    public void setBinding(FragmentSettingsBinding binding) {
        this.binding = binding;
        this.binding.setOnClick(this);
        this.binding.setForm(this.form);
    }

    @Override
    public void onClick(View v) {
        UserPreferences.setPreference(context, UserPreferences.USER_UNITY, form.getUnity());
        UserPreferences.setPreference(context, UserPreferences.USER_LANGUAGE, form.getLanguage());
        this.fragmentManager.popBackStack();
    }
}