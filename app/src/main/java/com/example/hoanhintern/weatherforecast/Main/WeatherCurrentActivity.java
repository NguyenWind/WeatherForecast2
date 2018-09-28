package com.example.hoanhintern.weatherforecast.Main;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoanhintern.weatherforecast.R;
import com.example.hoanhintern.weatherforecast.api.Listener;
import com.example.hoanhintern.weatherforecast.api.WeatherApiInfo;
import com.example.hoanhintern.weatherforecast.model.OpenWeatherJson;
import com.example.hoanhintern.weatherforecast.model.Weather;
import com.google.android.gms.common.internal.service.Common;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Locale;
import java.util.TimeZone;

public class WeatherCurrentActivity extends Activity {
    TextView tvCountry, tvMain, tvDay, tvHum, tvTemp, tvMax, tvMin, tvPress, tvRise, tvSet, tvWind, tvCloud;
    ImageView imgIcon;
    Listener listener;
    NumberFormat format = new DecimalFormat("#0");
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm a");
    double lon, lat;
    int key;
    WeatherApiInfo weatherApiInfo;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_location_weather);
        init();
        weatherApiInfo = new WeatherApiInfo(getApplicationContext());


        bundle = getIntent().getExtras();
        key = bundle.getInt("KEY");
        switch (key) {
            case 1:
                getWeatherCurrentData();

                break;
            case 2:
                getWeatherCountry();
                break;
            default:
                break;
        }

    }


    private void init() {
        tvCountry = (TextView) findViewById(R.id.tvCountry);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvHum = (TextView) findViewById(R.id.tvHum);
        tvMain = (TextView) findViewById(R.id.tvMain);
        tvTemp = (TextView) findViewById(R.id.tvTemp);
        tvMax = (TextView) findViewById(R.id.tvMax);
        tvMin = (TextView) findViewById(R.id.tvMin);
        tvPress = (TextView) findViewById(R.id.tvPress);
        tvRise = (TextView) findViewById(R.id.tvRise);
        tvWind = (TextView) findViewById(R.id.tvWind);
        tvSet = (TextView) findViewById(R.id.tvSet);
        tvCloud = (TextView) findViewById(R.id.tvCloud);
        imgIcon = (ImageView) findViewById(R.id.imgIcon);
    }

    private void getEvent() {
        listener = new Listener() {
            @Override
            public void results(OpenWeatherJson openWeatherJson) {

                tvCountry.setText(openWeatherJson.getName());
                tvDay.setText(dateFormat.format(openWeatherJson.getDt() * 1000));
                tvTemp.setText(format.format(openWeatherJson.getMain().getTemp() - 273.15) + "°");
                tvMain.setText(openWeatherJson.getWeather().get(0).getMain());
                tvMax.setText(format.format(openWeatherJson.getMain().getTemp_max() - 273.15) + "°");
                tvMin.setText(format.format(openWeatherJson.getMain().getTemp_min() - 273.15) + "°");
                tvWind.setText(openWeatherJson.getWind().getSpeed() + "m/s");
                tvHum.setText(format.format(openWeatherJson.getMain().getHumidity()) + "%");
                tvPress.setText(format.format(openWeatherJson.getMain().getPressure()) + "hpa");
                dateFormat2.setTimeZone(TimeZone.getTimeZone("Japan"));
                tvRise.setText(dateFormat2.format(openWeatherJson.getSys().getSunrise() * 1000));
                tvSet.setText(dateFormat2.format(openWeatherJson.getSys().getSunset() * 1000));
                tvCloud.setText(openWeatherJson.getWeather().get(0).getDescription());


            }

            @Override
            public void resultsBitmap(Bitmap bitmap) {

                    imgIcon.setImageBitmap(bitmap);

            }
        };
    }

    private void getWeatherCurrentData() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(WeatherCurrentActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                lon = location.getLongitude();
                lat = location.getLatitude();
            }

        }
        weatherApiInfo.getInforCurrentLocation(lat, lon);
        getEvent();
        weatherApiInfo.setOnlistener(listener);
    }

    private void getWeatherCountry() {
        String ads = bundle.getString("address");
        weatherApiInfo.getInforAddress(ads);
        getEvent();
        weatherApiInfo.setOnlistener(listener);


    }


}





