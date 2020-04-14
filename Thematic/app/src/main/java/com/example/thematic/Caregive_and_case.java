package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Caregive_and_case extends AppCompatActivity {
    private Button button11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_caregive_and_case);

        button11 = (Button) findViewById(R.id.button11);
        Button userBtn = (Button) findViewById(R.id.button11);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Caregive_and_case.this, Service_user_data.class);
                startActivity(intent);
            }
        });

    }
}
