package com.example.kieuvutrinh_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.kieuvutrinh_app.Adapter.Score_UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class HightScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView_highScore;
    private Score_UserAdapter score_userAdapter;
    private Button btn_quaylai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hight_score);
        btn_quaylai = findViewById(R.id.btn_quaylai);
        btn_quaylai.setOnClickListener(this);
        recyclerView_highScore = findViewById(R.id.rcv_bangHighScore);
        score_userAdapter = new Score_UserAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView_highScore.setLayoutManager(linearLayoutManager);
        score_userAdapter.setData(getList_ScoreUser());
        recyclerView_highScore.setAdapter(score_userAdapter);
    }

    private List<Score_User> getList_ScoreUser() {
        Intent i = getIntent();
        String username_user = i.getStringExtra("username_user");
        String score_user = i.getStringExtra("Score_user");
        List<Score_User> list_score_users = new ArrayList<>();
        list_score_users.add(new Score_User(username_user,Integer.parseInt(score_user)));
        list_score_users.add(new Score_User("Kiều Trình",10));
        return list_score_users;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_quaylai){
            Intent intent_n = new Intent(this,GiaodienGame_Activity.class);
            startActivity(intent_n);
        }
    }
}