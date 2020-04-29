package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        Button userBtn = (Button) findViewById(R.id.main_login);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this,Menu_for_user.class);
                startActivity(intent);
            }
        });

        Button registerBtn = (Button) findViewById(R.id.main_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Register.class);
                startActivity(intent);
            }
        });

        Button managerBtn = (Button) findViewById(R.id.button26);
        managerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Menu_for_manager.class);
                startActivity(intent);
            }
        });

        Button forgetBtn = (Button)findViewById(R.id.main_forget_password);
        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Forget_password.class);
                startActivity(intent);
            }
        });


        Button caregiverBtn = (Button)findViewById(R.id.button4);
        caregiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this,Menu_for_caregiver.class);
                startActivity(intent);
            }
        });


    }
}