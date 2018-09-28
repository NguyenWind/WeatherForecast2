package com.example.hoanhintern.weatherforecast.api;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.hoanhintern.weatherforecast.model.OpenWeatherJson;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class WeatherApiInfo {
    private static final String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String url2 = "http://api.openweathermap.org/data/2.5/weather?";
    private static final String key = "&appid=a88e0b03bbc0d4c599ed9bb3ff8b6d47";
    private String idIcon;
    Context context;

    private OpenWeatherJson openWeatherJson;
    Listener listener;

    public void setOnlistener(Listener listener) {
        this.listener = listener;
    }

    public WeatherApiInfo(Context context){
        this.context = context;
    }


    public void getInforCurrentLocation(double lat ,double lon){
        NumberFormat format = new DecimalFormat("#0.00");

        String linkCurrentLocation = url2+"lat="+lat+"&lon="+lon+key;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, linkCurrentLocation, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                openWeatherJson = new Gson().fromJson(response, OpenWeatherJson.class);
                Log.d("TAG", openWeatherJson.getName());
                listener.results(openWeatherJson);
                idIcon = openWeatherJson.getWeather().get(0).getIcon();
                String urlImage = "http://openweathermap.org/img/w/" + idIcon + ".png";
                ImageRequest imageRequest = new ImageRequest(urlImage, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response1) {
                        listener.resultsBitmap(response1);
                    }
                }, 0, 0, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getStackTrace();
                    }
                });
                Singleton.getInstance(context).getRequestQueue().add(imageRequest);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
            }
        });

        Singleton.getInstance(context).getRequestQueue().add(stringRequest);
    }

    public void getInforAddress(String q) {
        String linkAddress = url1 + q + key;

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, linkAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                openWeatherJson = new Gson().fromJson(response, OpenWeatherJson.class);
                Log.d("TAG", openWeatherJson.getName());
                listener.results(openWeatherJson);
                idIcon = openWeatherJson.getWeather().get(0).getIcon();
                String urlImage = "http://openweathermap.org/img/w/" + idIcon + ".png";
                ImageRequest imageRequest = new ImageRequest(urlImage, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response1) {
                        listener.resultsBitmap(response1);
                    }
                }, 0, 0, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getStackTrace();
                    }
                });
                Singleton.getInstance(context).getRequestQueue().add(imageRequest);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
            }
        });

        Singleton.getInstance(context).getRequestQueue().add(stringRequest);

        }



}

