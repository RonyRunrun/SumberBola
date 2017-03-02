package com.oktagon.sumberbola.sumberbola.Interface;

/**
 * Created by RR_PC on 2/19/2017.
 */

import com.oktagon.sumberbola.sumberbola.service.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();
}