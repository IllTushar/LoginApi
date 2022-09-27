package com.example.sampleretrofit.Api;

import com.example.sampleretrofit.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseModel>getData(
            @Field("email") String email,
            @Field("password") String password
    );
}
