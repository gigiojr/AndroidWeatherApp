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

/**
 * Class to make requests in OpenWeatherMap API.
 */
public class OpenWeatherMap {

    private static final String URL_ICON = "https://openweathermap.org/img/w/"; // URL to request icon image
    private static final String URL_API = "https://api.openweathermap.org/data/2.5/"; // Base URL
    private static final String URL_PARAM_API_KEY = "APPID=c6e381d8c7ff98f0fee43775817cf6ad"; // ID param
    private static final String URL_PARAM_LANG = "lang=pt"; // Language param
    private static final String URL_PARAM_UNIT = "units=metric"; // Unit of the response. Default: Kelvin, metric: Celsius, imperial: Fahrenheit

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
    public static void requestForecastWeatherByCityName(Context context, String cityName,
                                                        final RequestCallback callback){

        String url = URL_API + "forecast?" + URL_PARAM_API_KEY + "&q=" + cityName + "&" +
                URL_PARAM_LANG + "&" + URL_PARAM_UNIT;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
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

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    /**
     * To make an request on API to get forecast weather using latitude and longitude.
     *
     * @param context Application context.
     * @param latitude Latitude position.
     * @param longitude Longitude position.
     * @param callback Interface with functions that return the processed response.
     */
    public static void requestForecastWeatherByLocation(Context context, Double latitude, Double longitude,
                                                        final RequestCallback callback){

        String url = URL_API + "forecast?" + URL_PARAM_API_KEY + "&lat=" + String.valueOf(latitude) +
                "&lon=" + String.valueOf(longitude) + "&" + URL_PARAM_LANG + "&" + URL_PARAM_UNIT;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
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

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    /**
     * Interface to return elements of request after processing
     */
    public interface RequestCallback{
        void requestSuccess(ForecastModel forecastModel);
        void requestError();
    }
}
