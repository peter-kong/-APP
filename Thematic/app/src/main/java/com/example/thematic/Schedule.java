package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_manager_schedule);
    }
}
