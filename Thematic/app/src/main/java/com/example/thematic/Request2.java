package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class Request2 extends AppCompatActivity {

    private Spinner Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_request2);

        Time = (Spinner)findViewById(R.id.Time_spinner);
    }

    public void  order(View v){
        String[] Time = getResources().getStringArray(R.array.Time);
    }
}




