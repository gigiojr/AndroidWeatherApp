package br.com.weatherapp.ui.card;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.openweathermapapi.model.WeatherModel;
import br.com.weatherapp.BR;
import br.com.weatherapp.R;

/**
 * ViewModel class
 * Layout: layout/card_weather.xml
 */
public class WeatherCardViewModel {

    public String date;
    public String weatherIcon;
    public String weatherDescription;

    public String mainTemp;
    public String mainTempMin;
    public String mainTempMax;
    public String mainPressure;
    public String mainHumidity;

    public String windSpeed;

    public String cloudsAll;

    public WeatherCardViewModel(WeatherModel weatherModel){
        this.date = weatherModel.getDateFormated();
        this.weatherIcon = weatherModel.getIconUrl();
        this.weatherDescription = weatherModel.weatherDescription;
        this.mainTemp = String.valueOf(weatherModel.mainTemp) + " °C";
        this.mainTempMin = "Min: " + String.valueOf(weatherModel.mainTempMin) + " °C";
        this.mainTempMax = "Max: " + String.valueOf(weatherModel.mainTempMax) + " °C";
        this.mainHumidity = "Humidade: " + String.valueOf(weatherModel.mainHumidity) + "%";
        this.mainPressure = "Pressão: " + String.valueOf(weatherModel.mainPressure) + " hpa";
        this.windSpeed = "Vento: " + String.valueOf(weatherModel.windSpeed) + "m/s";
        this.cloudsAll = "Núvens: " + String.valueOf(weatherModel.cloudsAll) + "%";
    }

    /**
     * Controller of ImageView image.
     *
     * @param view ImageView component.
     * @param imageUrl Url of image.
     * @param imageError Image used when image in imageUrl is not available.
     */
    @BindingAdapter({"imageUrl", "imageError"})
    public static void loadImage(ImageView view, String imageUrl, Drawable imageError) {
        if(!imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).error(imageError).into(view);
        }
    }

    //********************************************************************************************//
    // Interface: RecycleMultiLayoutInterface
    //********************************************************************************************//
    @Override
    public int getVariable() {
        return BR.viewModel; // Variable of layout
    }

    @Override
    public int getLayout() {
        return R.layout.card_weather; // Layout
    }
}
