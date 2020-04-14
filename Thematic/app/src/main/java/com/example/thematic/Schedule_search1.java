package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Schedule_search1 extends AppCompatActivity {

    private Button schedule_morning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_schedule_search1);

        schedule_morning = (Button) findViewById(R.id.schedule_morning);
        Button nextBtn = (Button) findViewById(R.id.schedule_morning);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Schedule_search1.this, Schedule_search2.class);
                startActivity(intent);
            }
        });
    }
}
