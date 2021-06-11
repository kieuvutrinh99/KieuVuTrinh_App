package com.example.kieuvutrinh_app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Score_User {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("Score")
    @Expose
    private String score;

    public Score_User(String username, String score) {
        this.username = username;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
