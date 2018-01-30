package com.samapps.sachinmalik.koinexalarm.Alarm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Sachin Malik on 11/01/2018.
 */

public interface gitAPI {
    @GET("ticker")
    Call<TickerResponse> sendnotes();
}
