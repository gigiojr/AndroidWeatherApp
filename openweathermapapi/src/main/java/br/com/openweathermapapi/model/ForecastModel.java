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
    private static final String LABEL_CITY_COORD = "coord"; // Coord object
    private static final String LABEL_CITY_COORD_LAT = "lon"; // Latitude
    private static final String LABEL_CITY_COORD_LNG = "lat"; // Longitude

    public String cityName;
    public Double latitude;
    public Double longitude;
    public ArrayList<WeatherModel> weatherModelList;

    public ForecastModel(JSONObject json) throws JSONException {
        JSONObject city = json.getJSONObject(LABEL_CITY);
        if(city.has(LABEL_CITY_NAME)){
            this.cityName = city.getString(LABEL_CITY_NAME);

            JSONObject coord = city.getJSONObject(LABEL_CITY_COORD);
            if(coord.has(LABEL_CITY_COORD_LAT) && coord.has(LABEL_CITY_COORD_LNG)){
                this.latitude = coord.getDouble(LABEL_CITY_COORD_LAT);
                this.longitude = coord.getDouble(LABEL_CITY_COORD_LNG);
            }
        }

        this.weatherModelList = new ArrayList<>();
        JSONArray list = json.getJSONArray(LABEL_LIST);
        if(list.length() > 0){
            for(int i = 0; i < list.length(); i++){
                JSONObject item = list.getJSONObject(i);
                WeatherModel model = new WeatherModel(item);
                this.weatherModelList.add(model);
            }
        }
    }
}
