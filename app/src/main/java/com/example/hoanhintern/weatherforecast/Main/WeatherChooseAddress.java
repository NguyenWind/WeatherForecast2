package com.example.hoanhintern.weatherforecast.Main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hoanhintern.weatherforecast.R;

public class WeatherChooseAddress extends Activity {
        EditText edt;
        Button bt;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_choose_address);
        edt = (EditText)findViewById(R.id.edtAddress);
        bt = (Button)findViewById(R.id.btAddress);
        bundle = new Bundle();



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bundle.putString("address", String.valueOf(edt.getText()));
                int key = 2;
                bundle.putInt("KEY",key);
                Intent intent = new Intent(WeatherChooseAddress.this,WeatherCurrentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
