package br.com.weatherapp.ui.component;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import br.com.weatherapp.BR;

public class FieldButtonComponent extends BaseObservable implements View.OnClickListener{

    private String field;
    private FieldButtonComponentListener listener;

    public FieldButtonComponent(FieldButtonComponentListener listener){
        this.field = "";
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onFieldButtonClick(this.getField());
        this.setField("");
    }

    @Bindable
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.listener.onFieldChange(field);
        this.field = field;
        notifyPropertyChanged(BR.field);
    }

    public interface FieldButtonComponentListener{
        void onFieldChange(String value);
        void onFieldButtonClick(String value);
    }
}
