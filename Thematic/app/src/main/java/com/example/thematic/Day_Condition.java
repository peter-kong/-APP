package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Day_Condition extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_day__condition);
        getSupportActionBar().hide(); //隱藏標題

        final EditText month = (EditText)findViewById(R.id.輸入月份);
        final EditText day = (EditText)findViewById(R.id.輸入日期);
        final Button check = (Button)findViewById(R.id.檢視);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String Date = new String();
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        String input_month = month.getText().toString();
                        String input_day = day.getText().toString();
                        if(Integer.parseInt(input_month)<10){
                            if(Integer.parseInt(input_day)<10){
                                Date = "0"+input_month+"0"+input_day;
                            }
                            else if(Integer.parseInt(input_day)>=10){
                                Date = "0"+input_month+input_day;
                            }
                        }
                        else if(Integer.parseInt(input_month)>=10){
                            if(Integer.parseInt(input_day)<10){
                                Date = input_month+"0"+input_day;
                            }
                            if(Integer.parseInt(input_day)>=10){
                                Date = input_month+input_day;
                            }
                        }
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();;
                        obj.setPeopleUseRead(Date);

                        Intent intent = new Intent();
                        intent.setClass(Day_Condition.this, manager_people_use_search_day_read.class);
                        startActivity(intent);


                    }
                }).start();

            }
        });

    }
}
