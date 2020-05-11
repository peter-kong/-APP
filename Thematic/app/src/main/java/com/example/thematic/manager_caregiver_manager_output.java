package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class manager_caregiver_manager_output extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_caregiver_manager_output);
        getSupportActionBar().hide(); //隱藏標題
        final TextView time_view = (TextView)findViewById(R.id.時間顯示);
        final TextView user_view = (TextView)findViewById(R.id.使用者名稱顯示);
        final TextView work_view = (TextView)findViewById(R.id.服務內容顯示);
        GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
        ArrayList worktime = obj.returnScheduletime();
        String caregiverID = obj.returncaregiverID();
        String Date = obj.returnScheduleDate();
        String[] data = new String[worktime.size()];
        worktime.toArray(data);

        Log.e("Hi",""+worktime.size());
        final Spinner spnTime = (Spinner)findViewById(R.id.當日時間下拉選單);
        ArrayAdapter adapWeekList = new ArrayAdapter(manager_caregiver_manager_output.this, android.R.layout.simple_spinner_item, data);
        adapWeekList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTime.setAdapter(adapWeekList);

        spnTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                String selected = (String) spnTime.getSelectedItem();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList output_data = con.getschedule_work(Date,selected.substring(0,5),"我要caregiver工作內容",caregiverID);
                        time_view.post(new Runnable() {
                            public void run() {
                                time_view.setText(""+output_data.get(0)+"~"+output_data.get(1));
                            }
                        });
                        user_view.post(new Runnable(){
                            public void run(){
                                user_view.setText(""+output_data.get(2));
                            }
                        });
                        work_view.post(new Runnable() {
                            public void run() {
                                String work;
                                work = ""+output_data.get(3);
                                work = work.replace("、","\n");
                                work_view.setText(work);
                            }
                        });
                    }
                }).start();

            }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e("nothingSelected","沒有選擇內容");
                }
            });
    }
}
