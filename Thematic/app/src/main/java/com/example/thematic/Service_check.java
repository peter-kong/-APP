package com.example.thematic;

import android.os.Bundle;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

public class Service_check extends AppCompatActivity {

    private NumberPicker mNumberPicker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_service_check);

        final String[] Min = getResources().getStringArray(R.array.Min);

        mNumberPicker = (NumberPicker) findViewById(R.id.numberPicker_start_hour);
        mNumberPicker.setMinValue(8);
        mNumberPicker.setMaxValue(17);
        mNumberPicker.setValue(8);

        mNumberPicker = (NumberPicker) findViewById(R.id.numberPicker_start_min);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(Min.length - 1);
        mNumberPicker.setDisplayedValues(Min);


        mNumberPicker = (NumberPicker) findViewById(R.id.numberPicker_end_hour);
        mNumberPicker.setMinValue(8);
        mNumberPicker.setMaxValue(17);
        mNumberPicker.setValue(17);

        mNumberPicker = (NumberPicker) findViewById(R.id.numberPicker_end_min);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(Min.length - 1);
        mNumberPicker.setDisplayedValues(Min);
    }
}
