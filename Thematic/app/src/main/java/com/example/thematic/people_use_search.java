package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class people_use_search extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_people_use_search);
        getSupportActionBar().hide(); //隱藏標題
        Button sc = (Button)findViewById(R.id.月人力配置概況);
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent = new Intent();
                intent.setClass(people_use_search.this, Month_Condition.class);
                startActivity(intent);
            }
        });

        Button search = (Button)findViewById(R.id.日人力配置概況);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(people_use_search.this, Day_Condition.class);
                startActivity(intent);
            }
        });

    }
}
