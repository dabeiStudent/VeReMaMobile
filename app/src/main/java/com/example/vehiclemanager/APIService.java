package com.example.vehiclemanager;

import com.bumptech.glide.annotation.GlideExtension;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("signinmb")
    Call<LoginResponse> login (@Field("userName") String username, @Field("passWord") String password);

    @GET("getallaccmb")
    Call<AccountResponse> getacc();

    @GET("getallstaffmb")
    Call<StaffResponse> getstaff();
}
