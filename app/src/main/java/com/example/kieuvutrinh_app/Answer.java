package com.example.kieuvutrinh_app;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer { // class nayf doi thanh answer , sao k ddoir dc v

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("Kiemtra")
    @Expose
    private String kiemtra;
    @SerializedName("Id_question")
    @Expose
    private String id_question;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKiemtra() {
        return kiemtra;
    }

    public void setKiemtra(String kiemtra) {
        this.kiemtra = kiemtra;
    }

    public String getId_question() {
        return id_question;
    }

    public void setId_question(String id_question) {
        this.id_question = id_question;
    }
}
