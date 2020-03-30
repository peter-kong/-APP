package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Work_report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_work_report);
    }
}
