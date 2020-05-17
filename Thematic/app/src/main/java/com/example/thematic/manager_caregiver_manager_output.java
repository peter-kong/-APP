package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class manager_caregiver_manager_output extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_caregiver_manager_output);
        getSupportActionBar().hide(); //隱藏標題
        final TextView time_view = (TextView)findViewById(R.id.時間顯示);
        final TextView Date_view = (TextView)findViewById(R.id.日期顯示);
        final Spinner spnTime = (Spinner) findViewById(R.id.個案下拉選單);
        final TextView 完成度 = (TextView)findViewById(R.id.完成度);
        final TextView caregiverID = (TextView)findViewById(R.id.caregiverID);
        final TextView 備註 = (TextView)findViewById(R.id.備註顯示);
        final TextView work_view = (TextView)findViewById(R.id.服務內容顯示);

        GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
        String Date = obj.returnScheduleDate();
        Log.e("Date",""+Date);
        if(Date.equals("0000")){

        }
        else {
            Log.e("進入","");
            ArrayList name = obj.returnName();
            ArrayList UID = obj.returnUID();
            String[] 個案名單 = new String[name.size()];
            name.toArray(個案名單);
            //將所有日期資料放入spinner中

            ArrayAdapter adapWeekList = new ArrayAdapter(manager_caregiver_manager_output.this, R.layout.myspinner, 個案名單);
            adapWeekList.setDropDownViewResource(R.layout.myspinner);
            spnTime.setAdapter(adapWeekList);


            spnTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {

                    String selected = (String) spnTime.getSelectedItem();
                    int choose_index = spnTime.getSelectedItemPosition();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                            con.run();
                            String userID = "" + UID.get(choose_index);
                            Log.e("獲得資料","");
                            ArrayList data = con.getschedule_day(Date, "我要日工作內容", userID,obj.returnAcc());
                            Log.e("獲得資料成功","");
                            time_view.post(new Runnable() {
                                public void run() {
                                    time_view.setText("" + data.get(2) + "~" + data.get(3));
                                }
                            });

                            work_view.post(new Runnable() {
                                public void run() {
                                    String work;
                                    work = "" + data.get(5);
                                    work = work.replace("-", "\n");
                                    work_view.setText(work);
                                }
                            });

                            caregiverID.post(new Runnable() {
                                public void run() {
                                    String work;
                                    work = "" + data.get(4);
                                    caregiverID.setText(work);
                                }
                            });
                            完成度.post(new Runnable() {
                                public void run() {
                                    String work;
                                    work = "" + data.get(1);
                                    work = work.replace("、", "\n");
                                    完成度.setText(work);
                                }
                            });

                            Date_view.post(new Runnable() {
                                public void run() {
                                    Date_view.setText(Date);
                                }
                            });

                            備註.post(new Runnable() {
                                public void run() {
                                    備註.setText(""+data.get(0));
                                }
                            });

                        }
                    }).start();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.e("nothingSelected", "沒有選擇內容");
                }
            });
        }
    }
}
