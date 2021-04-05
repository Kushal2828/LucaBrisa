package com.luca.lucabrasi.APIclient;

import android.util.Log;


import com.luca.lucabrasi.Interfaces.OnDataResponseListner;
import com.luca.lucabrasi.Interfaces.RetrofitService;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommanAPI {
    private static final String TAG = "CommanAPI";
    String MethodName, responceData;
    public OnDataResponseListner onDataResponseListner;

    public CommanAPI(String methodName, OnDataResponseListner onDataResponseListner) {
        MethodName = methodName;
        this.onDataResponseListner = onDataResponseListner;
    }
    public void getResponse(String Url) {

        try {
            RetrofitService apiService = RetrofitClient.createService(RetrofitService.class);
            Call<ResponseBody> call = apiService.getData(Url);
            Log.e(TAG, "URL: " + call.request().url().toString() + "....");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null) {
                        try {
                            responceData = response.body().string();
                            Log.e(TAG, "onResponse: " + responceData);
                            onDataResponseListner.Response(MethodName, responceData, response.isSuccessful());
                        } catch (IOException e) {
                            Log.e("Exception = ", e.getMessage());
                            onDataResponseListner.Response(MethodName, responceData, response.isSuccessful());
                        }
                    } else {
                        onDataResponseListner.Response(MethodName, "NotReachable", false);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    onDataResponseListner.Response(MethodName, t.getMessage(), false);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "getResponse: " + e.getMessage());
        }

    }

    public void getResponse(String Url, String Parameter) {
        try {
            RetrofitService apiService = RetrofitClient.createService(RetrofitService.class);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (Parameter).toString());

            Call<ResponseBody> call = apiService.getResponseData(Url, body);
            Log.e(TAG, "URL: " + call.request().url().toString() + "...." + Parameter);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null) {
                        try {
                            responceData = response.body().string();
                            Log.e(TAG, "onResponse: " + responceData);
                            onDataResponseListner.Response(MethodName, responceData, response.isSuccessful());

                        } catch (IOException e) {
                            Log.e("Exception = ", e.getMessage());
                            onDataResponseListner.Response(MethodName, responceData, response.isSuccessful());
                        }
                    } else {
                        onDataResponseListner.Response(MethodName, "NotReachable", false);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    onDataResponseListner.Response(MethodName, "", false);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "getResponse: " + e.getMessage());
        }

    }

    public void postRequest(String Url, Map<String, String> params) {
        RetrofitService apiService = RetrofitClient.createService(RetrofitService.class);

        Call<ResponseBody> call2 = apiService.doGetUserList(Url, params);
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responceData = response.body().string();
                        Log.e(TAG, "onResponse: " + responceData);
                        onDataResponseListner.Response(MethodName, responceData, response.isSuccessful());

                    } catch (IOException e) {
                        Log.e("Exception = ", e.getMessage());
                        onDataResponseListner.Response(MethodName, responceData, response.isSuccessful());
                    }
                } else {
                    onDataResponseListner.Response(MethodName, "NotReachable", false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "getProducts:: " + t.toString());
                onDataResponseListner.Response(MethodName, "", false);
            }
        });
    }

    public void MultipartRequest(String url, MultipartBody requestBody) {
        RetrofitService apiService = RetrofitClient.createService(RetrofitService.class);

        Call<ResponseBody> responseBodyCall = apiService.uploadMultiFile(url, requestBody);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        responceData = response.body().string();
                        Log.e(TAG, "onResponse: " + responceData);
                        onDataResponseListner.Response(MethodName, responceData, response.isSuccessful());

                    } catch (IOException e) {
                        onDataResponseListner.Response(MethodName, "", false);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: " + t.toString());
                onDataResponseListner.Response(MethodName, "", false);
            }
        });

    }

}
