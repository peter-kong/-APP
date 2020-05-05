package com.example.thematic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu_for_user extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        Button nextPage5 = (Button)findViewById(R.id.下次照服時間);
        Button nextPage6 = (Button)findViewById(R.id.最近照服時間);
        Button nextPage7 = (Button)findViewById(R.id.歷史照服時間);
        Button nextPage8 = (Button)findViewById(R.id.個人資料);


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
