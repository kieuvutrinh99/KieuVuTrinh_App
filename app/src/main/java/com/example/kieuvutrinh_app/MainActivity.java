package com.example.kieuvutrinh_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_StartGame;
    private Button btn_HelpGame;
    private Button btn_HighScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        btn_StartGame.setOnClickListener(this);
        btn_HighScore.setOnClickListener(this);
        btn_HelpGame.setOnClickListener(this);
    }

    private void Init() {
        btn_StartGame = findViewById(R.id.btn_batdau);
        btn_HighScore = findViewById(R.id.btn_diemcao);
        btn_HelpGame = findViewById(R.id.btn_huongdan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_batdau:
                Intent intent = new Intent(MainActivity.this,GiaodienGame_Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_huongdan:
                break;
            case R.id.btn_diemcao:
                break;
        }
    }
}