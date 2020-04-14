package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_data_date extends AppCompatActivity {

    private Button button2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_user_data_date);

        button2 = (Button)findViewById(R.id.button2);

        Button NextpageBtn = (Button) findViewById(R.id.button2);

        NextpageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(User_data_date.this,Caregive_and_case .class);
                startActivity(intent);
            }
        });
    }




}
