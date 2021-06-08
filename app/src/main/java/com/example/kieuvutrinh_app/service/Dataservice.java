package com.example.kieuvutrinh_app.service;

import com.example.kieuvutrinh_app.Answer;
import com.example.kieuvutrinh_app.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Dataservice {
    @GET("getdata.php")
    Call<List<Question>> GetDataUser();

    @GET("getdataa.php")//no bi
    Call<List<Question>> GetDataCategory(@Query("idTheLoai") String id);

    @GET("getdataAnswer.php")//no bi
    Call<List<Answer>> GetDataAnswer(@Query("idQuestion") String id);
}
