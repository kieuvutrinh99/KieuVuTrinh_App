package com.example.kieuvutrinh_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button btn_Dangnhap,btn_Dangki;
    private EditText etTenDN,etMaukhau; // tên đăng nhập đây
    protected FirebaseAuth auth;
    private SharedPreferences sp; // cái này dùng để lưu data theo dạng json (key:value),một khi đã lưu thì chỉ có xóa ứng dụng
    //thì nó mới mất vì nó lưu thằng vào cái máy ảo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Init();
        auth = FirebaseAuth.getInstance();
        btn_Dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegistryActivity.class);
                startActivity(i);
            }
        });
        btn_Dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = etTenDN.getText().toString();
                String p = etMaukhau.getText().toString();
                if(u.isEmpty()){
                    etTenDN.setError("Tên đăng nhập không được để trống");
                    return;
                }
                if(p.isEmpty()){
                    etMaukhau.setError("Mật khẩu nhập không được để trống");
                    return;
                }
                auth.signInWithEmailAndPassword(u,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username_user",u);//usernmae là cái u này đúng kuh
                            sp = getSharedPreferences("dataUser",MODE_PRIVATE);//đây là tên của cái data mình đặt là dataUser
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("username", u); // lưu u vào trong editor
                            editor.commit();//xác nhận
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(getApplicationContext(),"Đăng nhập không thành công!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void Init() {
        btn_Dangnhap = findViewById(R.id.btn_Dangnhap);
        btn_Dangki = findViewById(R.id.btn_dangki);
        etTenDN = findViewById(R.id.et_tendangnhap);
        etMaukhau = findViewById(R.id.et_matkhau);
    }
}