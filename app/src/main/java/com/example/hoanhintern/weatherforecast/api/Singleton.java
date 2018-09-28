package com.example.hoanhintern.weatherforecast.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class Singleton {
    private static final String TAG = "VolleySingleton";
    private RequestQueue mRequestQueue;
    private static Singleton sInstance;


    private Singleton(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public static synchronized Singleton getInstance(Context context) {
        if (sInstance == null)
            sInstance = new Singleton(context);
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
