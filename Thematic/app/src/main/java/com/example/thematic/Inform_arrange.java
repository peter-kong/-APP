package com.example.thematic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Inform_arrange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_user_inform_arrange);
    }
}
