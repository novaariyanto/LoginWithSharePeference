package com.kejarkoding.loginsharepreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText username ,password;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        inisialisasi Edittext
        username =(EditText)findViewById(R.id.userlogin);
        password =(EditText)findViewById(R.id.passwordLogin);
//        inisialisasi buttton
        btnLogin = (Button)findViewById(R.id.btnLogin);
//        perintah saat btnLogin diklik
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                memeriksa apakah edittext kosong
                if(TextUtils.isEmpty(username.getText())||TextUtils.isEmpty(password.getText())){
                    if(TextUtils.isEmpty(username.getText())){
                        username.setError("Username belum diisi !");
                    }
                    if(TextUtils.isEmpty(password.getText())){
                        password.setError("Password belum diisi !");
                    }
                }else{
                    Login(username.getText().toString(),password.getText().toString());
                }
            }
        });
//        cek apakah status sudah login
        sharedPreferences = getSharedPreferences("loginSession",Context.MODE_PRIVATE);
        if(sharedPreferences.contains("username")){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

    }
    private void Login(String username,String password) {
//        menyimpan session di sharepreference
        sharedPreferences = getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.apply();

//        pindah ke mainactivity
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
