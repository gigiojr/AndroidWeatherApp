package br.com.openweathermapapi.model;

import android.text.format.DateFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import br.com.openweathermapapi.OpenWeatherMap;

/**
 * Class to represent a Weather object of the API using only main attributes to application.
 */
public class WeatherModel {
    private static final String LABEL_DATA = "dt"; // Date of forecast
    private static final String LABEL_WEATHER = "weather"; // Weather object
    private static final String LABEL_WEATHER_MAIN = "main"; // Group of weather parameters
    private static final String LABEL_WEATHER_ICON = "icon"; // Weather icon id
    private static final String LABEL_WEATHER_DESCRIPTION = "description"; // Weather condition within the group

    private static final String LABEL_MAIN = "main"; // main object
    private static final String LABEL_MAIN_TEMP = "temp"; // Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private static final String LABEL_MAIN_PRESSURE = "pressure"; // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
    private static final String LABEL_MAIN_HUMIDITY = "humidity"; // Humidity, %
    private static final String LABEL_MAIN_TEMP_MIN = "temp_min"; // Minimum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    private static final String LABEL_MAIN_TEMP_MAX = "temp_max"; // Maximum temperature at the moment. This is deviation from current temp that is possible for large cities and megalopolises geographically expanded (use these parameter optionally). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.

    private static final String LABEL_WIND = "wind"; // wind object
    private static final String LABEL_WIND_SPEED = "speed"; // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.

    private static final String LABEL_CLOUDS = "clouds"; // Cloudiness, %
    private static final String LABEL_CLOUDS_ALL = "all";

    private static final String LABEL_COORD = "coord"; // Coord object
    private static final String LABEL_COORD_LAT = "lat"; // Latitude
    private static final String LABEL_COORD_LNG = "lon"; // Longitude

    private static final String LABEL_SYS = "sys"; // Sys object
    private static final String LABEL_SYS_NAME = "name"; // City name

    public Date date;

    public String weatherMain;
    public String weatherIcon;
    public String weatherDescription;

    public int mainTemp;
    public int mainTempMin;
    public int mainTempMax;
    public int mainPressure;
    public int mainHumidity;

    public int windSpeed;

    public int cloudsAll;

    public String cityName;
    public Double latitude;
    public Double longitude;

    public WeatherModel(JSONObject json) throws JSONException {
        this.date = new Date(json.getLong(LABEL_DATA) * 1000);

        JSONArray weatherArray = json.getJSONArray(LABEL_WEATHER);
        if(weatherArray.length() > 0){
            JSONObject weather = weatherArray.getJSONObject(0);
            this.weatherMain = weather.getString(LABEL_WEATHER_MAIN);
            this.weatherIcon = weather.getString(LABEL_WEATHER_ICON);
            this.weatherDescription = weather.getString(LABEL_WEATHER_DESCRIPTION);
        }

        JSONObject main = json.getJSONObject(LABEL_MAIN);
        if(main.has(LABEL_MAIN_TEMP)){
            this.mainTemp = main.getInt(LABEL_MAIN_TEMP);
            this.mainTempMin = main.getInt(LABEL_MAIN_TEMP_MIN);
            this.mainTempMax = main.getInt(LABEL_MAIN_TEMP_MAX);
            this.mainPressure = main.getInt(LABEL_MAIN_PRESSURE);
            this.mainHumidity = main.getInt(LABEL_MAIN_HUMIDITY);
        }

        JSONObject wind = json.getJSONObject(LABEL_WIND);
        if(wind.has(LABEL_WIND_SPEED)){
            this.windSpeed = wind.getInt(LABEL_WIND_SPEED);
        }

        JSONObject clouds = json.getJSONObject(LABEL_CLOUDS);
        if(clouds.has(LABEL_CLOUDS_ALL)){
            this.cloudsAll = clouds.getInt(LABEL_CLOUDS_ALL);
        }

        if(json.has(LABEL_COORD)) {
            JSONObject coord = json.getJSONObject(LABEL_COORD);
            if (coord.has(LABEL_COORD_LAT) && coord.has(LABEL_COORD_LNG)) {
                this.latitude = coord.getDouble(LABEL_COORD_LAT);
                this.longitude = coord.getDouble(LABEL_COORD_LNG);
            }
        }

        if(json.has(LABEL_SYS)) {
            JSONObject sys = json.getJSONObject(LABEL_SYS);
            if (sys.has(LABEL_SYS_NAME)) {
                this.cityName = sys.getString(LABEL_SYS_NAME);
            }
        }
    }

    public String getIconUrl(){
        if(this.weatherIcon != null && !this.weatherIcon.isEmpty()) {
            return OpenWeatherMap.getUrlIcon(this.weatherIcon);
        } else{
            return this.weatherIcon;
        }
    }

    public String getDateFormatted(){
        if(this.date != null) {
            return (String) DateFormat.format("dd/MM/yyyy - hh:mm", this.date);
        }
        return "";
    }
}
