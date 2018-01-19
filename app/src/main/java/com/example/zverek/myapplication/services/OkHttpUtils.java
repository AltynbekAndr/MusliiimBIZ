package com.example.zverek.myapplication.services;

import okhttp3.OkHttpClient;

/**
 * Created by suleiman on 18.01.2018.
 */

public class OkHttpUtils {
    public static OkHttpClient okHttpClient = new OkHttpClient();
   public static OkHttpClient getInstance(){
        return okHttpClient;
    }
}
