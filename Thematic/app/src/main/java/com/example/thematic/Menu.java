package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button 補助規則分類,客製化,定位,使用紀錄,媒合功能,個人資料;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_user);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        客製化 = (Button)findViewById(R.id.客製化);
<<<<<<< HEAD
        定位 = (Button)findViewById(R.id.定位);
=======
>>>>>>> 90dfd2241a0db5ee2d88d9cbcc51f3ca52040d02
        媒合功能 = (Button)findViewById(R.id.媒合功能);
        個人資料 = (Button)findViewById(R.id.姓名);

        Button nextPage2 = (Button)findViewById(R.id.客製化);
<<<<<<< HEAD
        Button nextPage3 = (Button)findViewById(R.id.定位);
=======
>>>>>>> 90dfd2241a0db5ee2d88d9cbcc51f3ca52040d02
        Button nextPage5 = (Button)findViewById(R.id.媒合功能);
        Button nextPage6 = (Button)findViewById(R.id.姓名);
/*
        nextPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu.this,補助規則分類.class);
                startActivity(intent);
            }
        });

        nextPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu.this,客製化.class);
                startActivity(intent);
            }
        });

        nextPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu.this,定位.class);
                startActivity(intent);
            }
        });

        nextPage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu.this,使用紀錄.class);
                startActivity(intent);
            }
        });

        nextPage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu.this,媒合功能.class);
                startActivity(intent);
            }
        });
*/
        nextPage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu.this,PersonalData.class);
                startActivity(intent);
            }
        });
    }
}
