package com.example.kieuvutrinh_app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question { // class nayf doi thanh answer , sao k ddoir dc v

    @SerializedName("Id")
    @Expose
    private String id_answer;
    @SerializedName("Text_question")
    @Expose
    private String text_question;
    @SerializedName("Id_Category")
    @Expose
    private String id_category;

    public String getId_answer() {
        return id_answer;
    }

    public void setId_answer(String id_answer) {
        this.id_answer = id_answer;
    }

    public String getText_question() {
        return text_question;
    }

    public void setText_question(String text_question) {
        this.text_question = text_question;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }
}
