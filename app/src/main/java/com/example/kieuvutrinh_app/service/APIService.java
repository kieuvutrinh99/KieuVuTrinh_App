package com.example.kieuvutrinh_app.service;

public class APIService {
    public static String url ="http://10.0.2.2//KieuVuTrinh_btlmobile/"; // cái này  là cái đường link mà dẫn tới api , nếu thông thường
    //thì cái 10.0.2.2 kia nó  phải là localhost , nhưng ở đây m dùng máy ảo , ip mặc định của máy ảo là 10.0.2.2 , còn nếu dùng máy thật
    // ip sẽ là ip v4 của wifi mà điện thoại với máy tính cùng bắt

    public static  Dataservice getService(){
        return  APIRetrofitClient.getClient(url).create(Dataservice.class);

    }
}

