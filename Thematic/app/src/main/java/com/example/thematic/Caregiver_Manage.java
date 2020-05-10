package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Caregiver_Manage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver__manage);

        Button q = (Button) findViewById(R.id.工作內容查詢);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Caregiver_Manage.this, WorkContent_query.class);
                startActivity(intent);
            }
        });

        Button c = (Button) findViewById(R.id.日報與月報生成);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Caregiver_Manage.this, CreateMonthandDayRportForCaregiver.class);
                startActivity(intent);
            }
        });
    }
}
