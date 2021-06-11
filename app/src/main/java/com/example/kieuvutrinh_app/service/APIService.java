package com.example.kieuvutrinh_app.service;

public class APIService {
    //10.0.2.2
    //link api
    public static String url ="http://192.168.1.104//KieuVuTrinh_btlmobile/";

    public static  Dataservice getService(){
        return  APIRetrofitClient.getClient(url).create(Dataservice.class);

    }
}

