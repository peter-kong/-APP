package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Test_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity);
        final TextView text_view = (TextView) findViewById(R.id.DBtest);

        new Thread(new Runnable(){
            @Override
            public void run(){
                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                con.run();
                final String data = con.getData();
                Log.v("OK",data);
                text_view.post(new Runnable() {
                    public void run() {
                        text_view.setText(data);
                    }
                });

            }
        }).start();
    }

}

