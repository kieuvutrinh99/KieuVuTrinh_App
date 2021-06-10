package com.example.kieuvutrinh_app;

import java.io.Serializable;

public class Score_User implements Serializable {
    private String user_name;
    private int Score;

    public Score_User(String user_name, int score) {
        this.user_name = user_name;
        Score = score;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
