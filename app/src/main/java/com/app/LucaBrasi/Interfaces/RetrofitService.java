package com.app.LucaBrasi.Interfaces;


import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitService {


    @POST
    @FormUrlEncoded
    Call<ResponseBody> doGetUserList(@Url String url, @FieldMap Map<String, String> params);


    @POST
    Call<ResponseBody> uploadMultiFile(@Url String url, @Body RequestBody params);

    @Headers({"Content-Type: application/json"})
    @POST
    Call<ResponseBody> getResponseData(@Url String url, @Body RequestBody body);

    @GET
    Call<ResponseBody> getData(@Url String url);
}


