package com.samapps.sachinmalik.koinexalarm.Alarm;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sachin Malik on 30/01/2018.
 */

public class koinexAPI {

    private static String baseurl="https://koinex.in/api/";
    //Context context;
    public  static void ret(Callback<TickerResponse> callback) throws IOException {


        final OkHttpClient okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        gitAPI stackOverflowAPI = retrofit.create(gitAPI.class);


        Call<TickerResponse> call = stackOverflowAPI.sendnotes();

        call.enqueue(callback);
    }
}
