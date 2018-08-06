package com.kejarkoding.loginsharepreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView nama;
    Button btnLogout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        inisialisasi textview
        nama = (TextView)findViewById(R.id.welcome);
        sharedPreferences = getSharedPreferences("loginSession", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("username")){
            nama.setText("Selamat datang ! " + sharedPreferences.getString("username",null));
        }else{
            startActivity(new Intent(this,LoginActivity.class));
        }
        btnLogout = (Button)findViewById(R.id.btnlogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("loginSession",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
}
