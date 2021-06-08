package com.example.kieuvutrinh_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kieuvutrinh_app.service.APIService;
import com.example.kieuvutrinh_app.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Giaodien_QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_score_question;
    private TextView tv_content_question;
    private TextView tv_question;
    private TextView tv_question_one;
    private TextView tv_question_two;
    private TextView tv_question_three;
    private TextView tv_question_four;
    private String category;
    private int currentQuestion = 0;
    List<Answer> listAnswer ;
    private int i = -1;
    List<Question> listQuestion = null;
    Dataservice dataservice = APIService.getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien__question);
        category = getIntent().getStringExtra("category");
        Init();
        getDataListQuestion();
        tv_question_one.setOnClickListener(this);
        tv_question_two.setOnClickListener(this);
        tv_question_three.setOnClickListener(this);
        tv_question_four.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_question_one:
                tv_question_one.setBackgroundResource(R.drawable.bg_orange_corner_30);
                getBackgroundAnswer(tv_question_one,0);
                break;
            case R.id.tv_question_two:
                tv_question_two.setBackgroundResource(R.drawable.bg_orange_corner_30);
                getBackgroundAnswer(tv_question_two,1);
                break;
            case R.id.tv_question_three:
                tv_question_three.setBackgroundResource(R.drawable.bg_orange_corner_30);
                getBackgroundAnswer(tv_question_three,2);
                break;
            case R.id.tv_question_four:
                tv_question_four.setBackgroundResource(R.drawable.bg_orange_corner_30);
                getBackgroundAnswer(tv_question_four,3);
                break;

        }
    }

    private void getBackgroundAnswer(TextView textView,int index) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //textView.setBackgroundResource(R.drawable.bg_orange_corner_30);
                if(listAnswer.get(index).getKiemtra().equals("true")){
                    textView.setBackgroundResource(R.drawable.bg_green_corner_30);
                    nextQuestion();
                }
                else{
                    textView.setBackgroundResource(R.drawable.bg_red_corner_30);
                    showAnswerCorrect();
                    gameOver();
                }
            }
        },1000);
    }

    private void showAnswerCorrect() {
        if(listAnswer.get(0).getKiemtra().equals("true")){
            tv_question_one.setBackgroundResource(R.drawable.bg_green_corner_30);
        }
        if(listAnswer.get(1).getKiemtra().equals("true")){
            tv_question_two.setBackgroundResource(R.drawable.bg_green_corner_30);
        }
        if(listAnswer.get(2).getKiemtra().equals("true")){
            tv_question_three.setBackgroundResource(R.drawable.bg_green_corner_30);
        }
        if(listAnswer.get(3).getKiemtra().equals("true")){
            tv_question_four.setBackgroundResource(R.drawable.bg_green_corner_30);
        }
    }

    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Game Over");
            }
        },1000);
    }

    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Giaodien_QuestionActivity.this,GiaodienGame_Activity.class);
                dialog.dismiss();
                startActivity(intent);
            }

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void nextQuestion(){
        if (i < 4) { // i < max cau hoi thi no nhay tiep , luc nao them du 50 cau hoi thi i < 49
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    getDataAnswer();
                }
            }, 1000);
        }else{
            Toast.makeText(Giaodien_QuestionActivity.this,"Bạn đã hoàn thành bộ câu hỏi",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private void Init() {
        tv_content_question = findViewById(R.id.tv_conten_question);
        tv_question = findViewById(R.id.tv_question);
        tv_question_one = findViewById(R.id.tv_question_one);
        tv_question_two = findViewById(R.id.tv_question_two);
        tv_question_three = findViewById(R.id.tv_question_three);
        tv_question_four = findViewById(R.id.tv_question_four);
        tv_score_question = findViewById(R.id.tv_score_question);
    }

    private void getDataListQuestion() {
//            i++;
            Call<List<Question>> callback = dataservice.GetDataCategory(category);
            callback.enqueue(new Callback<List<Question>>() {
                @Override
                public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                    // nếu như có data về thì sẽ nhảy vào method  này
                    listQuestion = response.body();
                    getDataAnswer();
                    // ơ đây phải có cái apdapter , thôi t cứ sét vào vây
//                    tv_content_question.setText(listQuestion.get(i).getText_question());
                }

                @Override
                public void onFailure(Call<List<Question>> call, Throwable t) {
                    // nếu k có thì nhảy vảo trong đây
                    Toast.makeText(Giaodien_QuestionActivity.this,"Lỗi game rồi ",Toast.LENGTH_SHORT).show();//nhảy vào này tức là call faild
                }
            });
    }

    private void getDataAnswer(){
        currentQuestion++;
        tv_question.setText("Question "+currentQuestion);
        tv_score_question.setText("Score: "+(currentQuestion-1));
        i++;
        tv_question_one.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tv_question_two.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tv_question_three.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tv_question_four.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tv_content_question.setText(listQuestion.get(i).getText_question());
        //System.out.println(listQuestion.size());
        Call<List<Answer>> callback = dataservice.GetDataAnswer(listQuestion.get(i).getId_answer());
        callback.enqueue(new Callback<List<Answer>>() {
            @Override
            public void onResponse(Call<List<Answer>> call, Response<List<Answer>> response) {
                listAnswer = response.body();
                tv_question_one.setText(listAnswer.get(0).getContent());
                tv_question_two.setText(listAnswer.get(1).getContent());
                tv_question_three.setText(listAnswer.get(2).getContent());
                tv_question_four.setText(listAnswer.get(3).getContent());
            }

            @Override
            public void onFailure(Call<List<Answer>> call, Throwable t) {

            }
        });
    }


}