package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_for_caregiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_menu);
        getSupportActionBar().hide(); //隱藏標題

        /*Button NextpageBtn1 = (Button) findViewById(R.id.照顧需求查詢_care);
        NextpageBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_caregiver.this, Request2.class);
                startActivity(intent);
            }
        });

        Button NextpageBtn3 = (Button) findViewById(R.id.工作報表維護_care);
        NextpageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_caregiver.this, Work_report.class);
                startActivity(intent);
            }
        });*/

    }
}
