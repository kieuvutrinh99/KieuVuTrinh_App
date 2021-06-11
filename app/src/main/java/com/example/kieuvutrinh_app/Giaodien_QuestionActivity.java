package com.example.kieuvutrinh_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kieuvutrinh_app.service.APIService;
import com.example.kieuvutrinh_app.service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    private TextView tv_time;
    private String category;
    private int currentQuestion = 0;
    private CountDownTimer countDownTimer;
    private long time = 31000;
    List<Answer> listAnswer ;
    //private int i = -1;
    List<Question> listQuestion = null;
    Dataservice dataservice = APIService.getService();
    private SharedPreferences sp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien__question);
        category = getIntent().getStringExtra("category");
        sp = getApplicationContext().getSharedPreferences("dataUser", this.MODE_PRIVATE);
        Init();
        getDataListQuestion();
        tv_question_one.setOnClickListener(this);
        tv_question_two.setOnClickListener(this);
        tv_question_three.setOnClickListener(this);
        tv_question_four.setOnClickListener(this);

    }
//cái chỗ đặt hàm counttime dâu// cái chỗ  chọn đáp án ở đâu v
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
                    //trả lời đúng dừng countdown
                    countDownTimer.cancel();
                    nextQuestion();//đúng// nếu mà sau thì vẫn cứ trả lời tiếp đúng dừng luôn, thế phải chạy lại từ đầu à

                }
                else{
                    textView.setBackgroundResource(R.drawable.bg_red_corner_30);
                    //System.out.println(currentQuestion);  .............

                    showAnswerCorrect();
                    gameOver();//sai
                    postScore();
                }
            }
        },1000);
    }

    private void postScore() {
        Call<String> callback = dataservice.UpdateScore(sp.getString("username",""),currentQuestion - 1); // gọi api truyền các giá trị tương ứng vào là username với score
        callback.enqueue(new Callback<String>() {         // dòng trên đầu này là lấy ra cái username theo cái tên username vừa nãy mình lưu
            @Override                                    //nếu như giá trị lấy ra bằng null thì nó sẽ lấy giá trị mặc định để thay thế (defaultValue)
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    String str = response.body();
                    System.out.println(str);
                }
            }//có sao đâu,vuwafn ãy đ dc//tes cái post man kiểu gi đc r đấy chắc nãy m test api lỗi hay gì dó thôiok

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    //show đáp án đúng khi chọn sai
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
        //game kết thúc
    private void gameOver() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Game over\nĐiểm của bạn là: "+(currentQuestion-1));
            }
        },1000);
    }// diểm đâu
    // trả lời được hết bộ câu hỏi
    private void HoanThanhGame(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("CONGRATULATIONS\nBạn đã hoàn thành bộ câu hỏi\nĐiểm của bạn là: "+(currentQuestion-1));
                postScore();
                //sendScoreUser();
            }
        },1000);
    }
    //show message Dialog
    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Giaodien_QuestionActivity.this,HightScoreActivity.class);
                dialog.dismiss();
                startActivity(intent);
            }

        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void nextQuestion(){
        if (currentQuestion < listQuestion.size() ) { // nhỏ hơn số câu hỏi
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getDataAnswer();
                }
            }, 1000);//điểm là cái current Question àuh

        }
        else{
            currentQuestion++;//khi nào thì nó dừng lại , câu hỏi ấy
            HoanThanhGame();
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
        tv_time = findViewById(R.id.tv_time_question);
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
                }

                @Override
                public void onFailure(Call<List<Question>> call, Throwable t) {
                    // nếu k có thì nhảy vảo trong đây
                    Toast.makeText(Giaodien_QuestionActivity.this,"Lỗi game rồi ",Toast.LENGTH_SHORT).show();//nhảy vào này tức là call faild
                }
            });
    }

    private void getDataAnswer(){
        //currentQuestion++;
        startCountDownTimer();
        tv_question.setText("Question "+(currentQuestion+1));
        tv_score_question.setText("Score: "+(currentQuestion));
        //i++;
        tv_question_one.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tv_question_two.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tv_question_three.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tv_question_four.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tv_content_question.setText(listQuestion.get(currentQuestion).getText_question());
        //System.out.println(listQuestion.size());
        Call<List<Answer>> callback = dataservice.GetDataAnswer(listQuestion.get(currentQuestion).getId_answer());
        currentQuestion++;
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
    //hàm time đếm ngược trong câu hỏi
    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(time, 1000) {
            //chạy đến khi còn time
            public void onTick(long millisUntilFinished) {
                tv_time.setText("THỜI GIAN: " + millisUntilFinished / 1000);
                if(millisUntilFinished<=5000){
                    tv_time.setTextColor(Color.RED);
                }
                else tv_time.setTextColor(Color.BLACK);
            }
            //hết time nhảy vào onFinish
            public void onFinish() {
                showAnswerCorrect();
                gameOver();
            }
        }.start();
    }
}