package com.example.hoanhintern.weatherforecast.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hoanhintern.weatherforecast.R;


public class MainActivity extends Activity implements View.OnClickListener {

    LinearLayout currentLocation, chooseAddress;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {

        currentLocation = (LinearLayout) findViewById(R.id.itemCurrent);
        currentLocation.setOnClickListener(this);
        chooseAddress = (LinearLayout) findViewById(R.id.itemAddress);
        chooseAddress.setOnClickListener(this);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            return false;
        } else
            return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.itemCurrent:
                if (!isNetworkConnected()) {
                    Toast.makeText(this, "Internet is Off \n Please Connect to Internet", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Intent intent1 = new Intent(this, WeatherCurrentActivity.class);
                    int key = 1;
                    intent1.putExtra("KEY",key);
                    startActivity(intent1);
                }
                break;
            case R.id.itemAddress:
                if (!isNetworkConnected()) {
                    Toast.makeText(this, "Internet is Off \n Please Connect to Internet", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Intent intent2 = new Intent(this, WeatherChooseAddress.class);
                    startActivity(intent2);
                }
                break;
            default:
                break;
        }
    }
}

