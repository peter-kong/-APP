package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class manager_caregiver_manage_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_caregiver_manage_menu);
        getSupportActionBar().hide(); //隱藏標題

        Button weekBtn = (Button) findViewById(R.id.週工作報表查詢);
        weekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(manager_caregiver_manage_menu.this, manager_caregiver_manage_month_selected.class);
                startActivity(intent);
            }
        });

        Button dayBtn = (Button) findViewById(R.id.日工作報表查詢);
        dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(manager_caregiver_manage_menu.this, manager_caregiver_manage_day_selected.class);
                startActivity(intent);
            }
        });
    }
}
