package br.com.openweathermapapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.openweathermapapi.model.ForecastModel;
import br.com.openweathermapapi.model.WeatherModel;

/**
 * Class to make requests in OpenWeatherMap API.
 */
public class OpenWeatherMap {

    private static final String URL_ICON = "https://openweathermap.org/img/w/"; // URL to request icon image
    private static final String URL_API = "https://api.openweathermap.org/data/2.5/"; // Base URL
    private static final String URL_PARAM_API_KEY = "APPID=c6e381d8c7ff98f0fee43775817cf6ad"; // ID param
    private static final String URL_PARAM_UNIT = "units="; // Unit of the response. Default: Kelvin, metric: Celsius, imperial: Fahrenheit
    private static final String URL_PARAM_LANG = "lang=";

    private String unity;
    private String language;

    public OpenWeatherMap(String unity, String language){
        this.unity = unity == null || unity.isEmpty() ? "metric" : unity;
        this.language = language == null || language.isEmpty() ? "en" : language;
    }

    /**
     * Get complete URL to an icon string from response of request in own API.
     *
     * @param icon Icon received from API.
     * @return URL to get icon image.
     */
    public static String getUrlIcon(String icon){
        return URL_ICON + icon + ".png";
    }

    /**
     * Make an request on API to get forecast weather by the city name.
     *
     * @param context Application context.
     * @param cityName Name of city.
     * @param callback Interface with functions that return the processed response.
     */
    public void requestForecastWeatherByCityName(Context context, String cityName,
                                                        final RequestCallback callback){
        String url = URL_API + "forecast?" + URL_PARAM_API_KEY + "&q=" + cityName + "&" +
                URL_PARAM_UNIT + this.unity + "&" + URL_PARAM_LANG + this.language;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ForecastModel forecastModel = new ForecastModel(response);
                    callback.requestSuccess(forecastModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.requestError();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle requestError
                error.printStackTrace();
                callback.requestError();
            }
        });

        queue.add(jsonObjectRequest);
    }

    /**
     * Make an request on API to get forecast weather by the city name.
     *
     * @param context Application context.
     * @param cityName Name of city.
     * @param callback Interface with functions that return the processed response.
     */
    public void requestCurrentWeatherByCityName(Context context, String cityName,
                                                        final RequestCallback callback){
        String url = URL_API + "weather?" + URL_PARAM_API_KEY + "&q=" + cityName + "&" +
            URL_PARAM_UNIT + this.unity + "&" + URL_PARAM_LANG + this.language;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    WeatherModel model = new WeatherModel(response);
                    callback.requestSuccess(model);
                } catch (JSONException e) {
                    e.printStackTrace();
                    callback.requestError();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle requestError
                error.printStackTrace();
                callback.requestError();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    /**
     * Interface to return elements of request after processing
     */
    public interface RequestCallback{
        void requestSuccess(ForecastModel forecastModel);
        void requestSuccess(WeatherModel weatherModel);
        void requestError();
    }
}
