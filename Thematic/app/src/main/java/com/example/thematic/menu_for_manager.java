package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu_for_manager extends AppCompatActivity {

    private Button button22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_manager);


        button22 = (Button) findViewById(R.id.button22);

        Button registerBtn = (Button) findViewById(R.id.button22);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(menu_for_manager.this,CaregiverSelect.class);
                startActivity(intent);
            }
        });
    }

}
