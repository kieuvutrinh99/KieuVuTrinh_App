package com.example.kieuvutrinh_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistryActivity extends AppCompatActivity {
    private EditText etUserRegis,etPasswordRegis;
    private Button btnDangkyRegis,btn_CancelRegis;
    protected FirebaseAuth authRegis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        authRegis = FirebaseAuth.getInstance();
        Init();
        btnDangkyRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = etUserRegis.getText().toString();
                String p = etPasswordRegis.getText().toString();
                if(u.isEmpty()){
                    etUserRegis.setError("Tên đăng nhập không được để trống");
                    return;
                }
                if(p.isEmpty()){
                    etPasswordRegis.setError("Mật khẩu nhập không được để trống");
                    return;
                }
                if(p.length()<6){
                    etPasswordRegis.setError("Độ dài mật khẩu phải lớn hơn 6");
                    return;
                }
                authRegis.createUserWithEmailAndPassword(u,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Thành công!",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Không thành công!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btn_CancelRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Init() {
        etUserRegis = findViewById(R.id.et_tendangnhapRegistry);
        etPasswordRegis = findViewById(R.id.et_matkhauRegistry);
        btnDangkyRegis = findViewById(R.id.btn_regisDangky);
        btn_CancelRegis = findViewById(R.id.btn_regiscancer);
    }
}