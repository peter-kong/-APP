package com.example.thematic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Register extends AppCompatActivity {


    int check_bits = 0;
    int ch = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        getSupportActionBar().hide(); //隱藏標題

        Button reset = (Button) findViewById(R.id.register_reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Register.this, Register.class);
                startActivity(intent);
            }
        });

        Button register_confirm_btn = (Button) findViewById(R.id.register_confirm);

        register_confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final EditText UName_view = (EditText) findViewById(R.id.UName);
                        final EditText UAccount_view = (EditText) findViewById(R.id.UAccount);
                        final EditText UPassowrd_view = (EditText) findViewById(R.id.UPassword);
                        final EditText UID_view = (EditText) findViewById(R.id.UIDNumber);
                        final EditText UAddress_view = (EditText) findViewById(R.id.UAddress);
                        final EditText Uphone_view = (EditText) findViewById(R.id.UPhone);
                        final EditText UEmail_view = (EditText) findViewById(R.id.UAccount);
                        final EditText UMedHistory_view = (EditText) findViewById(R.id.UMedHistory);
                        final EditText ULevel_view = (EditText) findViewById(R.id.ULevel);
                        final EditText UGender_view = (EditText) findViewById(R.id.gender);
                        final EditText UBirth_view = (EditText) findViewById(R.id.UBirth);

                        String UGender = UGender_view.getText().toString();
                        String UName = UName_view.getText().toString();
                        String UAccount = UAccount_view.getText().toString();
                        String UPassword = UPassowrd_view.getText().toString();
                        String UID = UID_view.getText().toString();
                        String UAddress = UAddress_view.getText().toString();
                        String Uphone = Uphone_view.getText().toString();
                        String UEmail = UEmail_view.getText().toString();
                        String UMedHistory = UMedHistory_view.getText().toString();
                        String ULevel = ULevel_view.getText().toString();
                        String UBirth = UBirth_view.getText().toString();


                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        //con.insertRegisterData(UName, UAccount, UPassword, UIDNumber, UAddress, Uphone, UEmail, UMedHistory, ULevel, UBirth);

                        check_bits = con.insertRegisterData(UName, UAccount, UPassword, UID, UAddress, Uphone, UEmail, UMedHistory, ULevel, UBirth, UGender);

                    }
                }).start();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //過五秒後要做的事情
                        if(check_bits == 1){
                            new AlertDialog.Builder(Register.this)
                                    .setTitle("註冊成功")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent();
                                            intent.setClass(Register.this, User_tab.class);
                                            startActivity(intent);
                                        }
                                    }).setNegativeButton("cancel",null).create()
                                    .show();

                        }else if(check_bits == 0){
                            new AlertDialog.Builder(Register.this)
                                    .setTitle("帳號已經註冊或資料填寫不齊或網路無連線")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).setNegativeButton("cancel",null).create()
                                    .show();

                        }else if(check_bits == 2)//性別填錯
                        {
                            new AlertDialog.Builder(Register.this)
                                    .setTitle("性別格式不對")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).setNegativeButton("cancel",null).create()
                                    .show();
                        }else if(check_bits == 3)//身分證字號填錯
                        {
                            new AlertDialog.Builder(Register.this)
                                    .setTitle("身分證字號格式不對")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).setNegativeButton("cancel",null).create()
                                    .show();
                        }else if(check_bits == 4)//手機號碼填錯
                        {
                            new AlertDialog.Builder(Register.this)
                                    .setTitle("手機號碼格式不對")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).setNegativeButton("cancel",null).create()
                                    .show();
                        }

                    }}, 1000);

/*
                if(check_bits == 1){
                    new AlertDialog.Builder(Register.this)
                                   .setTitle("恭喜發財")
                                   .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setClass(Register.this, Menu_for_user.class);
                                        startActivity(intent);
                                    }
                                   }).setNegativeButton("cancel",null).create()
                                   .show();

                }else if(check_bits == 0 && ch == 1){
                    new AlertDialog.Builder(Register.this)
                            .setTitle("帳號已經註冊或資料填寫不齊")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setNegativeButton("cancel",null).create()
                            .show();
                    ch = 0;
                }else if(ch == 0){
                    ch = 1;
                    new AlertDialog.Builder(Register.this)
                            .setTitle("請仔細檢查後再一次按下確定 = . <")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setNegativeButton("cancel",null).create()
                            .show();
                }
*/
            }
        });
    }
}
