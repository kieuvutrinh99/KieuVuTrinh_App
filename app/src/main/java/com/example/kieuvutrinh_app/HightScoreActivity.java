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
import android.widget.TextView;
import android.widget.Toast;


import com.example.kieuvutrinh_app.Adapter.Score_UserAdapter;
import com.example.kieuvutrinh_app.service.APIService;
import com.example.kieuvutrinh_app.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HightScoreActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView_highScore;
    private Score_UserAdapter score_userAdapter;
    private Button btn_quaylai;
    //private TextView test;
    List<Score_User> list ;
    //private List<Score_User> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hight_score);
        //test = findViewById(R.id.test);
        btn_quaylai = findViewById(R.id.btn_quaylai);
        btn_quaylai.setOnClickListener(this);
        recyclerView_highScore = findViewById(R.id.rcv_bangHighScore);
        score_userAdapter = new Score_UserAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView_highScore.setLayoutManager(linearLayoutManager);
        //score_userAdapter.setData(list);
        getData();
        recyclerView_highScore.setAdapter(score_userAdapter);

    }

        private void getData() {
            Dataservice dataservice = APIService.getService();
            Call<List<Score_User>> callback = dataservice.getDataScore();
            callback.enqueue(new Callback<List<Score_User>>() {
                @Override
                public void onResponse(Call<List<Score_User>> call, Response<List<Score_User>> response) {
                    // nếu như có data về thì sẽ nhảy vào method  này
                     list = response.body();
                    System.out.println(list.size());
                    score_userAdapter.setData(list);
                }

                @Override
                public void onFailure(Call<List<Score_User>> call, Throwable t) {
                }
            });
        }

    private List<Score_User> getList_ScoreUser() {
        List<Score_User> list_score_users = new ArrayList<>();
        list_score_users.add(new Score_User("kietri","10"));
        System.out.println(list_score_users.size());
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