package com.example.thematic;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Menu_for_user extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        Button nextPage5 = (Button)findViewById(R.id.下次照服時間);
        Button nextPage6 = (Button)findViewById(R.id.上次照服時間);
        Button nextPage7 = (Button)findViewById(R.id.歷史照服時間);
        Button nextPage8 = (Button)findViewById(R.id.個人資料);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //取得user的歷史日期資料並放入全域變數中
                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                con.run();
                Time t = new Time();
                t.setToNow();
                int month = t.month + 1;
                int day = t.monthDay;
                String Date = "";
                if (month < 10) {
                    if (day < 10) {
                        Date = "0" + month + "0" + day;
                    } else if (day >= 10) {
                        Date = "0" + month + day;
                    }
                } else if (month >= 10) {
                    if (day < 10) {
                        Date = month + "0" + day;
                    } else if (day >= 10) {
                        Date = "" + month + day;
                    }
                }
                String[] str = null;
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                String user帳號 = obj.returnAcc();
                ArrayList HistoryDate = new ArrayList();
                HistoryDate = con.GetDate(Date, user帳號);
                GlobalVariable_Account 歷史日期保存 = (GlobalVariable_Account)getApplicationContext();
                歷史日期保存.setDate(HistoryDate);
            }
        }).start();
        GlobalVariable_Account 帳號保存物件 = (GlobalVariable_Account)getApplicationContext();
        Log.e("不同 activity test",帳號保存物件.returnAcc());


        nextPage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_user.this, Next_Inform_arrange.class);
                startActivity(intent);
            }
        });

        nextPage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_user.this, Recent_Inform_arrange.class);
                startActivity(intent);
            }
        });

        nextPage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_user.this, History_Inform_arrange.class);
                startActivity(intent);
            }
        });

        nextPage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_user.this, Personal_data.class);
                startActivity(intent);
            }
        });
    }
}
