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
    @GET("getallcusmb")
    Call<CustomerResponse> getcustomer();
    @FormUrlEncoded
    @POST("updateprofilestaff")
    Call<SubmitResponse> updatestaff (@Field("ten") String ten,@Field("username") String username, @Field("sdt") String sdt, @Field("dia_chi") String dia_chi);

    @FormUrlEncoded
    @POST("updateprofilecustomer")
    Call<SubmitResponse> updatescustomer (@Field("ten") String ten,@Field("username") String username, @Field("sdt") String sdt, @Field("dia_chi") String dia_chi);

    @FormUrlEncoded
    @POST("addneworder")
    Call<SubmitResponse> addneworder (@Field("maNv") int maNv,@Field("soXe") String soXe, @Field("tenXe") String tenXe,@Field("moTa") String moTa ,@Field("tenKh") String tenKh,
                                      @Field("ngaynhan") String ngayNhan, @Field("thoiGian") String thoiGian,@Field("dichVu") int dichVu, @Field("tongTien") String tongTien,
                                      @Field("diaChi") String diaChi, @Field("soDt") String soDt,@Field("tenTk") String tenTk, @Field("matKhau") String matKhau);

    @GET("getallordermb")
    Call<OrderResponse> getorder();
}
