package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                            check = 1;
                            Log.v("Login","caregiver登入成功");
                            intent.setClass(Login.this,Menu_for_manager.class);
                            startActivity(intent);

                        }
                        else if((con.getData(Account,"我要user密碼").equals(Password))){
                            Intent intent = new Intent();
                            check = 2;
                            Log.v("Login","user登入成功");
                            intent.setClass(Login.this,Menu_for_user.class);
                            startActivity(intent);

                        }
                        else if (check == 0){
                            Log.e("Login_Failed","登入失敗");
                        }
                    }
                }).start();
            }
        });

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