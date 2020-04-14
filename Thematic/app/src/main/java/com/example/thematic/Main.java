package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    private Button button2;
    private Button button10;
    private Button button26;
    private Button main_forget_password;
    private Button test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        button2 = (Button) findViewById(R.id.main_login);
        Button userBtn = (Button) findViewById(R.id.main_login);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Menu_for_user.class);
                startActivity(intent);
            }
        });

        button10 = (Button) findViewById(R.id.main_register);
        Button registerBtn = (Button) findViewById(R.id.main_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Register.class);
                startActivity(intent);
            }
        });

        button26 = (Button)findViewById(R.id.button26);
        Button managerBtn = (Button) findViewById(R.id.button26);
        managerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Menu_for_manager.class);
                startActivity(intent);
            }
        });

        main_forget_password = (Button)findViewById(R.id.main_forget_password);
        Button forgetBtn = (Button)findViewById(R.id.main_forget_password);
        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Forget_password.class);
                startActivity(intent);
            }
        });

        test = (Button)findViewById(R.id.test);
        Button testBtn = (Button)findViewById(R.id.test);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Main.this, Test_activity.class);
                startActivity(intent);
            }
        });


    }
}