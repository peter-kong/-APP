package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PersonalData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        getSupportActionBar().hide(); //隱藏標題

    }
}
