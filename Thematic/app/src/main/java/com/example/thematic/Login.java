package com.example.thematic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends AppCompatActivity {

    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide(); //隱藏標題
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

                        Log.e("Account",Account);

                        if((con.getData(Account,"我要manager密碼").equals(Password))){
                            check = 3;
                            Intent intent = new Intent();
                            Log.v("Login","manager登入成功");
                            intent.setClass(Login.this,Manager_tab.class);
                            startActivity(intent);
                        }
                        else if((con.getData(Account,"我要caregiver密碼")).equals(Password)){
                            check = 1;
                            Intent intent = new Intent();
                            Log.v("Login","caregiver登入成功");
                            intent.setClass(Login.this,Caregiver_tab.class);
                            startActivity(intent);
                        }
                        else if((con.getData(Account,"我要user密碼").equals(Password))){
                            check = 2;
                            Intent intent = new Intent();
                            Log.v("Login","user登入成功");
                            intent.setClass(Login.this,User_tab.class);
                            startActivity(intent);
                        }else{
                            check = 0;
                        }
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

                    }}, 2000);

            }
        });

        /*
        //介面優化....有Bug
        ConstraintLayout Main_layout = (ConstraintLayout)findViewById(R.id.Main_layout);
        Button Login = (Button)findViewById(R.id.main_login);

        public void addLayoutListener(final View Main_layout,final View scroll){
            Main_layout.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener(){
                        @Override
                        public Void onGlobolLayout() {
                            Rect rect = new Rect();

                            Main_layout.getWindowVisibleDisplayFrame(rect);

                            int InvisibleHeight = Main_layout.getRootView().getHeight() - rect.bottom;

                            if (InvisibleHeight > 100) {
                                int[] location = new int[2];
                                scroll.getLocationInWindow(location);

                                int srollHeight = (location[1] + scroll.getHeight()) - rect.bottom;

                                Main_layout.scrollTo(0, srollHeight);
                            } else {
                                Main_layout.scrollTo(0, 0);
                            }
                        }
                    });
        }*/





        //Test for Socket
        /*

        ViewGroup.LayoutParams params = 測試.getLayoutParams();
        params.width = 3000;
        params.height = 2000;
        測試.setLayoutParams(params);

        */
        /*


        Button 測試 = (Button) findViewById(R.id.Transfer);
        測試.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this,SocketTest.class);
                startActivity(intent);
            }
        });*/


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


        /*Button test = (Button) findViewById(R.id.Test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, manager_user_manage_menu.class);
                startActivity(intent);
            }
        });*/


        /*Button managerBtn = (Button) findViewById(R.id.managertest);
        managerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Manager_tab.class);
                startActivity(intent);
            }
        });*/

        /*Button userBtn = (Button) findViewById(R.id.usertest);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, User_tab.class);
                startActivity(intent);
            }
        });*/


       /* Button caregiverBtn = (Button)findViewById(R.id.caretest);
        caregiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this,Caregiver_tab.class);
                startActivity(intent);
            }
        });*/



    }
}