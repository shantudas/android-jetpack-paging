package com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    private static final String BASE_URL = "https://newsapi.org/";
    private static RetrofitRequest mInstance;
    private Retrofit retrofit;


    private RetrofitRequest() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitRequest getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitRequest();
        }
        return mInstance;
    }

    public ApiRequest getApi() {
        return retrofit.create(ApiRequest.class);
    }
}

