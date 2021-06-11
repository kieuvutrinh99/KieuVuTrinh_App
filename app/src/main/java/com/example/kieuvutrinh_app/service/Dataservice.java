package com.example.kieuvutrinh_app.service;

import com.example.kieuvutrinh_app.Answer;
import com.example.kieuvutrinh_app.Question;
import com.example.kieuvutrinh_app.Score_User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Dataservice {

    @GET("getdataa.php")
    Call<List<Question>> GetDataCategory(@Query("idTheLoai") String id);

    @GET("getdataAnswer.php")
    Call<List<Answer>> GetDataAnswer(@Query("idQuestion") String id);

    @FormUrlEncoded
    @POST("update_score.php")
    Call<String> UpdateScore(@Field("username") String username,
                               @Field("score") int score
                               ) ;
    @GET("getdataScore.php")
    Call<List<Score_User>> getDataScore();
}
