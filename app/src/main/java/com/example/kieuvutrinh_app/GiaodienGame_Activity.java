package com.example.kieuvutrinh_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GiaodienGame_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_lichsu,btn_dichbenh,btn_vanhoa,btn_trangchu;
    private String username_user;
    //private boolean checkStarGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien_game_);
        Init();
    }

    private void Init() {
        btn_lichsu = findViewById(R.id.btn_lichsu);
        btn_dichbenh = findViewById(R.id.btn_dichbenh);
        btn_vanhoa = findViewById(R.id.btn_vanhoa);
        btn_trangchu = findViewById(R.id.back_home);
        btn_lichsu.setOnClickListener(this);//cai man hinh nay la activity nao,màn hfinh đang mở đấy
        btn_dichbenh.setOnClickListener(this);
        btn_vanhoa.setOnClickListener(this);
        btn_trangchu.setOnClickListener(this);
//        Intent i = getIntent();
//        username_user = i.getStringExtra("username_user");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(GiaodienGame_Activity.this,Giaodien_QuestionActivity.class);
        intent.putExtra("username_user",username_user);
        //Intent intent2 = new Intent
        switch (v.getId()){
            case R.id.btn_lichsu:
                intent.putExtra("category","1");
                //intent.putExtra("checkStarGame","true");
                startActivity(intent);
                break;
            case R.id.btn_vanhoa:
                intent.putExtra("category","2");
               // intent.putExtra("checkStarGame","true");
                startActivity(intent);
                break;
            case R.id.btn_dichbenh:
                intent.putExtra("category","3");
              //  intent.putExtra("checkStarGame","true");
                startActivity(intent);
                break;
            case R.id.back_home:
                Intent intent1 = new Intent(this,MainActivity.class);
                startActivity(intent1);
                break;
        }
    }
}