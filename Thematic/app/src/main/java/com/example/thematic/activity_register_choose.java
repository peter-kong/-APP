package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class activity_register_choose extends AppCompatActivity {

    private Button button11;
    private Button button12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_choose);

        button11=(Button) findViewById(R.id.button11);
        Button managerBtn = (Button) findViewById(R.id.button11);
        managerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(activity_register_choose.this, Activity_register.class);
                startActivity(intent);
            }
        });

        button12 = (Button) findViewById(R.id.button12);
        Button userBtn = (Button) findViewById(R.id.button12);
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(activity_register_choose.this, activity_register1.class);
                startActivity(intent);
            }
        });
    }
}
