package com.hardcopy.btchat.network;

import com.google.gson.JsonObject;
import com.hardcopy.btchat.model.SaveInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DELL on 2016-12-01.
 */

public class HttpReqManager{
    private static HttpReqManager uniqueInstance = new HttpReqManager();
    private static final String USER_AGENT = "Mozilla/5.0";

    private static Retrofit     mRetrofit;
    private static RestService  mRestService;


    private static int rest;

    public HttpReqManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://ubuntu@ec2-52-78-169-204.ap-northeast-2.compute.amazonaws.com:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestService = mRetrofit.create(RestService.class);
    }
    public static void postSaveMoney(SaveInfo saveInfo, final CallbackEvent<JsonObject> callback){
        Call<JsonObject> call = mRestService.postSaveMoney(saveInfo);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                try {
                    callback.onSuccess(response.body());
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onSuccess(null);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError();
            }
        });
    }


    public static HttpReqManager getInstance() {
        return uniqueInstance;
    }
}
