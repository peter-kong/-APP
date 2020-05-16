package com.example.thematic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
                        String Date = new String();
                        if(Integer.parseInt(inputMonth)<10){
                                Date = "0"+inputMonth+"00";
                        }
                        else if(Integer.parseInt(inputMonth)>=10){
                            Date = inputMonth+"00";
                        }
                        ArrayList month_date = new ArrayList();
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        month_date = con.GetDate(Date,caregiverID,"我要caregiver本月日期");
                        GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                        obj.setAccount(caregiverID);
                        obj.setDate(month_date);
                        obj.setScheduleDate(Date);
                        Intent intent = new Intent();
                        if(month_date.size() == 0){
                            month_date.add("無資料");
                            obj.setDate(month_date);
                            intent.setClass(manager_caregiver_manage_month_selected.this,
                                    manager_caregiver_manage_month_output.class);
                            startActivity(intent);
                        }
                        else {
                            intent.setClass(manager_caregiver_manage_month_selected.this,
                                    manager_caregiver_manage_month_output.class);
                            startActivity(intent);
                        }
                    }
                }).start();
            }
        });
    }
}
