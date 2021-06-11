package com.example.kieuvutrinh_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Context;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int NOTIFICATION_ID = 1;
    private Button btn_StartGame;
    private Button btn_HelpGame;
    private Button btn_HighScore;
    private String username_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        btn_StartGame.setOnClickListener(this);
        btn_HighScore.setOnClickListener(this);
        btn_HelpGame.setOnClickListener(this);

        Intent i = getIntent();
        username_user = i.getStringExtra("username_user");
//        Intent intent = new Intent(this,Giaodien_QuestionActivity.class);
//        intent.putExtra("username_user",username_user);
//        startActivity(intent);
        //Toast.makeText(this,username_user,Toast.LENGTH_SHORT).show();
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
                intent.putExtra("username_user",username_user);
                startActivity(intent);
                break;
            case R.id.btn_huongdan:
                Intent intent3 = new Intent(MainActivity.this, HelpGameActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_diemcao:
                Intent intent2 = new Intent(MainActivity.this, HightScoreActivity.class);
                startActivity(intent2);
                break;
        }
    }


}