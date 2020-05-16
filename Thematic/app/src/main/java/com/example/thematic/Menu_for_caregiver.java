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

                        tmp2.setUID(con.getUserUID(照服員帳號,strDate));//找照服員當天要照顧的人的UID
                        tmp2.setName(con.getName(tmp2.returnUID()));//用要照顧的人的UID，並且把UID轉換成人名，再把人名放入全域變數中
                        tmp.setTommorrowoToday(true);
                        Intent intent = new Intent();
                        intent.setClass(Menu_for_caregiver.this, caregiver_work_report_maintain.class);
                        startActivity(intent);
                        tmp2.println();
                    }
                }).start();
/*
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(tmp.returnUID().size() == 0) {
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
                    }, 1000);

*/

            }
        });
            Button NextpageBtn3 = (Button) findViewById(R.id.本日工作報表_care);
            NextpageBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(Menu_for_caregiver.this, caregiver_work_report_search.class);
                    startActivity(intent);
                }
            });
        }
    }
