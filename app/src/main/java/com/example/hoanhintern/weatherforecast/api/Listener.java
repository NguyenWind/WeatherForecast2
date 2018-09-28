package com.example.hoanhintern.weatherforecast.api;

import android.graphics.Bitmap;

import com.example.hoanhintern.weatherforecast.model.OpenWeatherJson;

public interface Listener {

    void results(OpenWeatherJson openWeatherJson);

    void resultsBitmap(Bitmap bitmap);
}
