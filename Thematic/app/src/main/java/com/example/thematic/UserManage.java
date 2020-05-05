package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserManage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage);

        Button NextpageBtn4 = (Button) findViewById(R.id.個案資料查詢);
        NextpageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UserManage.this, UserDataQuery.class);
                startActivity(intent);
            }
        });

        Button c = (Button) findViewById(R.id.個人資料及服務報表生成);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UserManage.this, CreateMonthandDayRportForUser.class);
                startActivity(intent);
            }
        });
    }
}
