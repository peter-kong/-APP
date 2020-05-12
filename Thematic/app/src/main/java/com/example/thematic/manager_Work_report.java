package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.sql.Date;
import java.util.Arrays;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class manager_Work_report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_work_report);

        final Spinner 個案Spn = (Spinner)findViewById(R.id.個案下拉選單);
        final TextView 日期 = (TextView) findViewById(R.id.日期顯示);
        final TextView 工作時間 = (TextView)findViewById(R.id.工作時間);
        final TextView 照服員名字 = (TextView)findViewById(R.id.照服員名字);
        final TextView 服務內容 = (TextView)findViewById(R.id.服務內容顯示);
        final TextView 備註 = (TextView)findViewById(R.id.備註顯示);
        final TextView 完成度 = (TextView)findViewById(R.id.完成度);
        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
        ArrayList Name = obj.returnName();
        String Date = obj.returnScheduleDate();
        Log.e("manager_Work_report","->"+Date);
        if(Date.equals("0000")){

        }
        else {
            ArrayAdapter adapWeekList = new ArrayAdapter(manager_Work_report.this, android.R.layout.simple_spinner_item, Name);
            adapWeekList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            個案Spn.setAdapter(adapWeekList);
            個案Spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    String Date = obj.returnScheduleDate();
                    if (Date.equals("00")) {

                    } else {
                        int choose_index = 個案Spn.getSelectedItemPosition();
                        ArrayList UID = obj.returnUID();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                                con.run();
                                ArrayList data = con.getschedule(Date, "我要排程工作內容", "" + UID.get(choose_index));

                                日期.post(new Runnable() {
                                    public void run() {
                                        日期.setText(Date);
                                    }
                                });
                                工作時間.post(new Runnable() {
                                    public void run() {
                                        String time = data.get(2) + "~" + data.get(3);
                                        工作時間.setText(time);
                                    }
                                });

                                照服員名字.post(new Runnable() {
                                    public void run() {
                                        照服員名字.setText("" + data.get(4));
                                    }
                                });
                                完成度.post(new Runnable() {
                                    public void run() {
                                        完成度.setText(("" + data.get(1)).replace("、", "\n"));
                                    }
                                });
                                服務內容.post(new Runnable() {
                                    public void run() {
                                        服務內容.setText(("" + data.get(5)).replace("、", ":\n") + ":");
                                    }
                                });

                                備註.post(new Runnable() {
                                    public void run() {
                                        備註.setText("" + data.get(0));
                                    }
                                });

                            }
                        }).start();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e("nothingSelected", "沒有選擇內容");
                }
            });
        }
    }
}
