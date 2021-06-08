package com.example.kieuvutrinh_app;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView tv_content_question;
    private TextView tv_question;
    private TextView tv_question_one;
    private TextView tv_question_two;
    private TextView tv_question_three;
    private TextView tv_question_four;
    private String category;
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
                if (listAnswer.get(0).getKiemtra().equals("true")){
                    Toast.makeText(Giaodien_QuestionActivity.this,"ban chon dung",Toast.LENGTH_SHORT).show();
                    nextQuestion();
                    // m lam them 1 caci màù nuwaxx, chon sai background đáp án thành màu đỏ,ok
                }else{
                    Toast.makeText(Giaodien_QuestionActivity.this,"ban chon sai",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_question_two:
                if (listAnswer.get(1).getKiemtra().equals("true")){
                    Toast.makeText(Giaodien_QuestionActivity.this,"ban chon dung",Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }else{
                    Toast.makeText(Giaodien_QuestionActivity.this,"ban chon sai",Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.tv_question_three:
                if (listAnswer.get(2).getKiemtra().equals("true")){
                    Toast.makeText(Giaodien_QuestionActivity.this,"ban chon dung",Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }else{
                    Toast.makeText(Giaodien_QuestionActivity.this,"ban chon sai",Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.tv_question_four:
                if (listAnswer.get(3).getKiemtra().equals("true")){
                    Toast.makeText(Giaodien_QuestionActivity.this,"ban chon dung",Toast.LENGTH_SHORT).show();
                    nextQuestion();

                }else{
                    Toast.makeText(Giaodien_QuestionActivity.this,"ban chon sai",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    private void nextQuestion(){
        if (i < 1) { // i < max cau hoi thi no nhay tiep , luc nao them du 50 cau hoi thi i < 49
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getDataAnswer();
                }
            }, 2000);
        }else{
            Toast.makeText(Giaodien_QuestionActivity.this,"Bạn đã hoàn thành bộ câu hỏi",Toast.LENGTH_SHORT).show();
        }
    }
    private void Init() {
        tv_content_question = findViewById(R.id.tv_conten_question);
        tv_question = findViewById(R.id.tv_question);
        tv_question_one = findViewById(R.id.tv_question_one);
        tv_question_two = findViewById(R.id.tv_question_two);
        tv_question_three = findViewById(R.id.tv_question_three);
        tv_question_four = findViewById(R.id.tv_question_four);
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
        i++;
        tv_content_question.setText(listQuestion.get(i).getText_question());
        System.out.println(listQuestion.size());
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