package br.com.openweathermapapi.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class to represent a forecast object of the API using only main attributes to application.
 */
public class ForecastModel {
    private static final String LABEL_LIST = "list"; // List of Weather

    private static final String LABEL_CITY = "city"; // City object
    private static final String LABEL_CITY_NAME = "name"; // Name of city

    public String cityName;
    public ArrayList<WeatherModel> weatherModelList;

    public ForecastModel(JSONObject json) throws JSONException {
        JSONObject city = json.getJSONObject(LABEL_CITY);
        if(city != null){
            this.cityName = city.getString(LABEL_CITY_NAME);
        }

        this.weatherModelList = new ArrayList<>();
        JSONArray list = json.getJSONArray(LABEL_LIST);
        if(list != null && list.length() > 0){
            for(int i = 0; i < list.length(); i++){
                JSONObject item = list.getJSONObject(i);
                WeatherModel model = new WeatherModel(item);
                this.weatherModelList.add(model);
            }
        }
    }
}
