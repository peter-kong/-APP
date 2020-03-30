package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_for_manager extends AppCompatActivity {

    private Button 照顧需求查詢;
    private Button 照護服務時間排程;
    private Button 工作報表維護;
    private Button 服務使用者資料維護;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_menu);
        getSupportActionBar().hide(); //隱藏標題

        照顧需求查詢 = (Button) findViewById(R.id.照顧需求查詢);
        照護服務時間排程 = (Button) findViewById(R.id.照護服務時間排程);
        工作報表維護 = (Button)findViewById(R.id.工作報表維護);
        服務使用者資料維護 =(Button)findViewById(R.id.服務使用者資料維護);

        /*Button NextpageBtn1 = (Button) findViewById(R.id.照顧需求查詢);
        NextpageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_manager.this, Caregiver_select.class);
                startActivity(intent);
            }
        });*/

        Button NextpageBtn2 = (Button) findViewById(R.id.照護服務時間排程);
        NextpageBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_manager.this, Schedule.class);
                startActivity(intent);
            }
        });

        Button NextpageBtn3 = (Button) findViewById(R.id.工作報表維護);
        NextpageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_manager.this, Work_report.class);
                startActivity(intent);
            }
        });

        Button NextpageBtn4 = (Button) findViewById(R.id.服務使用者資料維護);
        NextpageBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Menu_for_manager.this, Caregiver_selection.class);
                startActivity(intent);
            }
        });


    }

}
