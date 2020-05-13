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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Menu_for_caregiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_menu);
        getSupportActionBar().hide(); //隱藏標題

        Button NextpageBtn1 = (Button) findViewById(R.id.明日工作報表_care);
        NextpageBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                GlobalVariable_Account tmp = (GlobalVariable_Account)getApplicationContext();
                Log.e("Line 37","Enter");
                //Log.e("明日工作報表",tmp.returnUID().get(0).toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String 照服員帳號 = tmp.returnAcc();
                        GlobalVariable_Account tmp2 = (GlobalVariable_Account)getApplicationContext();

                        SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
                        Date date = new Date();
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(date);
                        calendar.add(calendar.DATE, 1);
                        date = calendar.getTime();
                        String strDate = sdFormat.format(date);
                        Log.e("明日",strDate);

                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        Log.e("strDateformenu",strDate);

                        tmp2.setUID(con.getUserUID(照服員帳號,strDate));
                        tmp2.setName(con.getName(tmp2.returnUID()));
                        tmp.setTommorrowoToday(true);

                        tmp2.println();
                       // Log.e("tmpreturn", tmp.returnUID().get(0).toString());

                        if(!tmp.returnUID().get(0).toString().equals("No data")){
                            Log.e("tag","Tomorrow");
                            Intent intent = new Intent();
                            intent.setClass(Menu_for_caregiver.this, caregiver_normal_work_report1.class);
                            startActivity(intent);
                        }


                    }
                }).start();


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(tmp.returnUID().get(0).toString().equals("No data")) {
                                new AlertDialog.Builder(Menu_for_caregiver.this)
                                        .setTitle("沒有個資喔!!")
                                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).setNegativeButton("cancel", null).create()
                                        .show();
                            }
                        }
                    }, 3000);



            }
        });

        Button NextpageBtn3 = (Button) findViewById(R.id.本日工作報表_care);
        NextpageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        GlobalVariable_Account tmp = (GlobalVariable_Account)getApplicationContext();
                        String 照服員帳號 = tmp.returnAcc();
                        GlobalVariable_Account tmp2 = (GlobalVariable_Account)getApplicationContext();

                        //設時間
                        SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
                        Date date = new Date();
                        String strDate = sdFormat.format(date);
                        Log.e("本日",strDate);
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        tmp2.setUID(con.getUserUID(照服員帳號,strDate));

                        tmp2.setName(con.getName(tmp2.returnUID()));
                        //tmp2.println();


                        tmp.setTommorrowoToday(false);

                        Intent intent = new Intent();
                        intent.setClass(Menu_for_caregiver.this, caregiver_normal_work_report1.class);
                        startActivity(intent);
                    }
                }).start();


            }
        });
    }
}
