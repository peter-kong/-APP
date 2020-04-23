package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class Schedule_search1 extends AppCompatActivity {

    private NumberPicker mNumberPicker_start_hour;
    private NumberPicker mNumberPicker_start_min;
    private NumberPicker mNumberPicker_end_hour;
    private NumberPicker mNumberPicker_end_min;
    private Button Schedule_Search_送出;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_schedule_search1);

        final String[] Min = getResources().getStringArray(R.array.Min);

        mNumberPicker_start_hour = (NumberPicker) findViewById(R.id.numberPicker_start_hour);
        mNumberPicker_start_hour.setMinValue(8);
        mNumberPicker_start_hour.setMaxValue(17);
        mNumberPicker_start_hour.setValue(10);

        mNumberPicker_start_min = (NumberPicker) findViewById(R.id.numberPicker_start_min);
        mNumberPicker_start_min.setMinValue(0);
        mNumberPicker_start_min.setMaxValue(Min.length - 1);
        mNumberPicker_start_min.setDisplayedValues(Min);


        mNumberPicker_end_hour = (NumberPicker) findViewById(R.id.numberPicker_end_hour);
        mNumberPicker_end_hour.setMinValue(8);
        mNumberPicker_end_hour.setMaxValue(17);
        mNumberPicker_end_hour.setValue(14);

        mNumberPicker_end_min = (NumberPicker) findViewById(R.id.numberPicker_end_min);
        mNumberPicker_end_min.setMinValue(0);
        mNumberPicker_end_min.setMaxValue(Min.length - 1);
        mNumberPicker_end_min.setDisplayedValues(Min);

        Button NextpageBtn = (Button) findViewById(R.id.Schedule_Search_送出);
        NextpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Schedule_search1.this, Schedule_search2.class);
                startActivity(intent);
            }
        });
    }
}
