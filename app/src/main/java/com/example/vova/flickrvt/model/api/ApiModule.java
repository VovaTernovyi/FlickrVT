package com.example.vova.flickrvt.model.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vova on 27.01.17.
 */

public class ApiModule {

    public static final String ENDPOINT = "https://api.flickr.com/services/";

    public static ApiInterface getApiInterface() {

        ApiInterface requestInterface = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface.class);
        return requestInterface;
    }
}