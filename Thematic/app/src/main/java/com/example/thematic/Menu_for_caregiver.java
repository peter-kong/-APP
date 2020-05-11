package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

                Intent intent = new Intent();
                intent.setClass(Menu_for_caregiver.this, caregiver_normal_work_report1.class);
                startActivity(intent);
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

                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        tmp2.setUID(con.getUserUID(照服員帳號));
                        tmp2.setName(con.getName(tmp2.returnUID()));
                        //tmp2.println();

                        Intent intent = new Intent();
                        intent.setClass(Menu_for_caregiver.this, caregiver_normal_work_report1.class);
                        startActivity(intent);
                    }
                }).start();


            }
        });


        Button NextpageBtn4 = (Button) findViewById(R.id.歷史工作報表_care);
        NextpageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_caregiver.this, caregiver_normal_work_report2.class);
                startActivity(intent);
            }
        });

    }
}
