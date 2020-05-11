package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class manager_caregiver_manage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_caregiver_manage);
        getSupportActionBar().hide(); //隱藏標題

        EditText ID = (EditText)findViewById(R.id.輸入照服員ID);
        EditText Month = (EditText)findViewById(R.id.輸入月份);
        EditText Day = (EditText)findViewById(R.id.輸入日期);

        Button searchBtn = (Button) findViewById(R.id.查詢);
        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String caregiverID = ""+ID.getText().toString();
                        String inputMonth = ""+Month.getText().toString();
                        String inputDay = ""+Day.getText().toString();
                        String Date = new String();
                        if(Integer.parseInt(inputMonth)<10){
                            if(Integer.parseInt(inputDay)<10){
                                Date = "0"+inputMonth+"0"+inputDay;
                            }
                            else if(Integer.parseInt(inputDay)>=10){
                                Date = "0"+inputMonth+inputDay;
                            }
                        }
                        else if(Integer.parseInt(inputMonth)>=10){
                            if(Integer.parseInt(inputDay)<10){
                                Date = inputMonth+"0"+inputDay;
                            }
                            else if(Integer.parseInt(inputDay)>=10){
                                Date = inputMonth+inputDay;
                            }
                        }
                        ArrayList data = new ArrayList();
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        data = con.getschedule(Date,"我要caregiver工作時間",caregiverID);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setScheduletime(data,caregiverID,Date);

                        Intent intent = new Intent();
                        intent.setClass(manager_caregiver_manage.this, manager_caregiver_manager_output.class);
                        startActivity(intent);
                    }
                }).start();



            }
        });
    }
}
