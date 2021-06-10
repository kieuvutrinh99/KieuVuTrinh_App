package com.example.kieuvutrinh_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;


import com.example.kieuvutrinh_app.Adapter.Score_UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class HightScoreActivity extends AppCompatActivity {
    private RecyclerView recyclerView_highScore;
    private Score_UserAdapter score_userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hight_score);
        recyclerView_highScore = findViewById(R.id.rcv_bangHighScore);
        score_userAdapter = new Score_UserAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView_highScore.setLayoutManager(linearLayoutManager);
        score_userAdapter.setData(getList_ScoreUser());
        recyclerView_highScore.setAdapter(score_userAdapter);
    }

    private List<Score_User> getList_ScoreUser() {
        List<Score_User> list_score_users = new ArrayList<>();
        list_score_users.add(new Score_User("Kiều Trình",10));
        list_score_users.add(new Score_User("Kiều Trình",11));
        list_score_users.add(new Score_User("Kiều Trình",10));
        list_score_users.add(new Score_User("Kiều Trình",10));

        return list_score_users;
    }
}