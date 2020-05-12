package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class caregiver_work_report_search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_work_report_search);

        final TextView 月份 = (TextView) findViewById(R.id.輸入月份);
        final TextView 日期 = (TextView) findViewById(R.id.輸入日期);
        final Button 查詢 = (Button) findViewById(R.id.查詢);

        查詢.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String Date = new String();
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        String input_month;
                        String input_day;
                        if(月份.getText().toString().matches("")){
                            input_month = "0";
                        }
                        else {
                            input_month = 月份.getText().toString();
                        }
                        if(日期.getText().toString().matches("")){
                            input_day = "0";
                        }
                        else {
                            input_day = 日期.getText().toString();
                        }
                        if (Integer.parseInt(input_month) < 10) {
                            if (Integer.parseInt(input_day) < 10) {
                                Date = "0" + input_month + "0" + input_day;
                            } else if (Integer.parseInt(input_day) >= 10) {
                                Date = "0" + input_month + input_day;
                            }
                        } else if (Integer.parseInt(input_month) >= 10) {
                            if (Integer.parseInt(input_day) < 10) {
                                Date = input_month + "0" + input_day;
                            }
                            if (Integer.parseInt(input_day) >= 10) {
                                Date = input_month + input_day;
                            }
                        }
                        GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                        String 帳號 = obj.returnAcc();
                        ArrayList UID = con.getworkreport_UID(Date, "我要caregiver當日的UID",帳號);
                        ArrayList Name = con.getName(UID);
                        obj.setUID(UID);
                        obj.setName(Name);
                        obj.setScheduleDate(Date);
                        Intent intent = new Intent();
                        intent.setClass(caregiver_work_report_search.this, caregiver_history_work_report.class);
                        startActivity(intent);

                    }
                }).start();
            }
        });
    }
}