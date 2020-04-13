package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Schedule extends AppCompatActivity {

    private Button 排程結果查詢;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_schedule);
        getSupportActionBar().hide(); //隱藏標題

        Button nextBtn = (Button) findViewById(R.id.排程結果查詢);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Schedule.this, Schedule_search.class);
                startActivity(intent);
            }
        });
    }
}
