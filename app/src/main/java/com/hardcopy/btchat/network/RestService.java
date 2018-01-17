package com.hardcopy.btchat.network;

import com.google.gson.JsonObject;
import com.hardcopy.btchat.model.SaveInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by DELL on 2016-12-01.
 */

public interface RestService {

    @POST("zoomoney/save")
    Call<JsonObject> postSaveMoney(@Body SaveInfo saveInfo);
}