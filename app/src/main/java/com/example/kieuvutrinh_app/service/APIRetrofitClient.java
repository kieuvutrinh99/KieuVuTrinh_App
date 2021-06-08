package com.example.kieuvutrinh_app.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String base_url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(10000, TimeUnit.MILLISECONDS) // chờ quá lâu mà server k trả dữ liệu về thì sẽ ngắt(thoi gian ngat la 10 giay)
                .writeTimeout(10000,TimeUnit.MILLISECONDS)
                .connectTimeout(10000,TimeUnit.MILLISECONDS)//đợi quá 10 giây sẽ ngắt kết nối
                .retryOnConnectionFailure(true) // nếu lỗi mạng sẽ cố gắng connect lại
                .protocols(Arrays.asList(Protocol.HTTP_1_1)) //set lai giao thuc de tranh cac trg hop khi khong the ket noi  dc voi 1 so giao thuc ( vd nhu https ,http) se sinh ra loi
                .build();

        Gson gson = new GsonBuilder().setLenient().create(); //convert data api tra ve ve dang tu khoa cua java

        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)//url
                .client(okHttpClient)//cau hinh cac tuong tac cua mang
                .addConverterFactory(GsonConverterFactory.create(gson))//convert data api tra ve ve dang java
                .build();

        return retrofit;
    }
}
