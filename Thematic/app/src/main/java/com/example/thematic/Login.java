package com.example.thematic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import java.net.UnknownHostException;

public class Login extends AppCompatActivity {

    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        Button LoginBtn = (Button) findViewById(R.id.main_login);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText 登入帳號 = (EditText)findViewById(R.id.editText5);
                EditText 登入密碼 = (EditText)findViewById(R.id.editText7);
                GlobalVariable_Account 帳號保存物件 = (GlobalVariable_Account)getApplicationContext();
                帳號保存物件.setAccount(登入帳號.getText().toString().trim());
                //Log.e("帳號",帳號保存物件.returnAcc());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        String Account = ""+登入帳號.getText().toString()+"";
                        String Password = ""+登入密碼.getText().toString()+"";

                        if((con.getData(Account,"我要manager密碼")).equals(Password)){
                            Log.e("get,Password",Password+","+(con.getData(Account,"我要manager密碼")));
                            Intent intent = new Intent();
                            Log.v("Login","caregiver登入成功");
                            intent.setClass(Login.this,Menu_for_manager.class);
                            startActivity(intent);
                        }
                        else if((con.getData(Account,"我要user密碼").equals(Password))){
                            Intent intent = new Intent();
                            Log.v("Login","user登入成功");
                            intent.setClass(Login.this,Menu_for_user.class);
                            startActivity(intent);
                        }

                        check = 1;

                    }
                }).start();



                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //過五秒後要做的事情
                        Log.e("Login_Failed","登入失敗");
                        if(check == 0) {
                            new AlertDialog.Builder(Login.this)
                                    .setTitle("登入失敗")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).setNegativeButton("cancel", null).create()
                                    .show();
                        }
                    }}, 1000);

            }
        });


        //Test for Socket
        Button 測試 = (Button) findViewById(R.id.Transfer);
        測試.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this,SocketTest.class);
                startActivity(intent);
            }
        });


/*
        //Test for 動態新增元件測試
        Button 測試 = (Button) findViewById(R.id.動態新增元件測試);
        測試.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this,Dynamic_button.class);
                startActivity(intent);
            }
        });
*/





        Button registerBtn = (Button) findViewById(R.id.main_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Register.class);
                startActivity(intent);
            }
        });

        Button managerBtn = (Button) findViewById(R.id.button26);
        managerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Menu_for_manager.class);
                startActivity(intent);
            }
        });

        Button forgetBtn = (Button)findViewById(R.id.main_forget_password);
        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Forget_password.class);
                startActivity(intent);
            }
        });

        Button caregiverBtn = (Button)findViewById(R.id.button4);
        caregiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this,Menu_for_caregiver.class);
                startActivity(intent);
            }
        });
    }
}