package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_for_manager extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_menu);
        getSupportActionBar().hide(); //隱藏標題

        Button NextpageBtn1 = (Button) findViewById(R.id.照服員管理);
        NextpageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_manager.this, manager_caregiver_manage.class);
                startActivity(intent);
            }
        });

        Button NextpageBtn3 = (Button) findViewById(R.id.工作報表維護);
        NextpageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_manager.this, Work_report.class);
                startActivity(intent);
            }
        });

        Button NextpageBtn4 = (Button) findViewById(R.id.個案資料查詢);
        NextpageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_manager.this, manager_user_search.class);
                startActivity(intent);
            }
        });

        Button NextpageBtn5 = (Button) findViewById(R.id.輸入個案需求);
        NextpageBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_manager.this, Service_check.class);
                startActivity(intent);
            }
        });

        Button ScheduleBtn = (Button) findViewById(R.id.人力配置查詢);
        ScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_manager.this, people_use_search.class);
                startActivity(intent);
            }
        });
    }
}


