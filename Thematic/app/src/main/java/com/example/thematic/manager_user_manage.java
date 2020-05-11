package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class manager_user_manage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_user_manage);
        getSupportActionBar().hide(); //隱藏標題
        Button user_Search = (Button)findViewById(R.id.個案資料查詢);
        user_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(manager_user_manage.this, manager_user_search.class);
                startActivity(intent);
            }
        });


    }
}
