package br.com.weatherapp.ui.fragment.settings;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import br.com.weatherapp.BR;

public class SettingForm extends BaseObservable {
    private String unity;
    private String language;

    public SettingForm(String unity, String language){
        this.unity = unity;
        this.language = language;
    }

    @Bindable
    public String getLanguage() {
        return language;
    }

    @Bindable
    public String getUnity() {
        return unity;
    }

    public void setLanguage(String language) {
        this.language = language;
        notifyPropertyChanged(BR.language);
    }

    public void setUnity(String unity) {
        this.unity = unity;
        notifyPropertyChanged(BR.unity);
    }
}
