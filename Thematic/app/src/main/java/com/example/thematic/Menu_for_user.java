package com.example.thematic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu_for_user extends AppCompatActivity {

    private Button 補助規則分類,客製化,定位,使用紀錄,排程通知,個人資料;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        客製化 = (Button)findViewById(R.id.服務管理維護);
        排程通知 = (Button)findViewById(R.id.排程通知);
        個人資料 = (Button)findViewById(R.id.姓名);

        Button nextPage2 = (Button)findViewById(R.id.服務管理維護);
        Button nextPage5 = (Button)findViewById(R.id.排程通知);
        Button nextPage6 = (Button)findViewById(R.id.姓名);

        排程通知 = (Button)findViewById(R.id.排程通知);
        個人資料 = (Button)findViewById(R.id.姓名);
        nextPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_user.this, Service_check.class);
                startActivity(intent);
            }
        });

        nextPage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_user.this, Inform_arrange.class);
                startActivity(intent);
            }
        });

        nextPage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_user.this, Personal_data.class);
                startActivity(intent);
            }
        });
    }
}
