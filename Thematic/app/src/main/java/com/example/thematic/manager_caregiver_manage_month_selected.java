package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class manager_caregiver_manage_month_selected extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_caregiver_manage_month_selected);
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
                        String caregiverID;
                        String inputMonth;
                        String inputDay;
                        if(ID.getText().toString().matches("")){
                            caregiverID = "0";
                        }
                        else if(ID.getText().toString().matches(" ")){
                            caregiverID = "0";
                        }
                        else {
                            caregiverID = ID.getText().toString();
                        }
                        if(Month.getText().toString().matches("")){
                            inputMonth = "0";
                        }
                        else if(Month.getText().toString().matches(" ")){
                            inputMonth = "0";
                        }
                        else {
                            inputMonth = Month.getText().toString();
                        }
                        if(Day.getText().toString().matches("")){
                            inputDay = "0";
                        }
                        else if(Day.getText().toString().matches(" ")){
                            inputDay = "0";
                        }
                        else {
                            inputDay = Day.getText().toString();
                        }

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
                        intent.setClass(manager_caregiver_manage_month_selected.this,
                                manager_caregiver_manager_output.class);
                        startActivity(intent);
                    }
                }).start();



            }
        });
    }
}
