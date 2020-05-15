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

public class caregiver_history_work_report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_history_work_report);
        getSupportActionBar().hide(); //隱藏標題

        final TextView time_view = (TextView) findViewById(R.id.Time);
        final TextView caregiver_view = (TextView) findViewById(R.id.workername);
        final TextView 日期 = (TextView) findViewById(R.id.個案日期);
        final Spinner 個案下拉選單 = (Spinner) findViewById(R.id.個案下拉選單);
        final TextView 服務內容 = (TextView)findViewById(R.id.服務內容);
        final TextView 完成度 = (TextView)findViewById(R.id.完成度);
        final TextView 備註 = (TextView)findViewById(R.id.備註  );

        //取全域的歷史日期的資料
        GlobalVariable_Account obj1 = (GlobalVariable_Account) getApplicationContext();
        String Date = obj1.returnScheduleDate();
        if(Date.equals("0000")){
        }
        else {
            ArrayList name = obj1.returnName();
            ArrayList UID = obj1.returnUID();
            String[] 個案名單 = new String[name.size()];
            name.toArray(個案名單);
            //將所有日期資料放入spinner中

            ArrayAdapter datelist = new ArrayAdapter(caregiver_history_work_report.this,R.layout.myspinner, 個案名單);
            datelist.setDropDownViewResource(R.layout.myspinner);
            個案下拉選單.setAdapter(datelist);

            //spinner被選擇值的時候動作
            個案下拉選單.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {

                    //拿到被選擇项的值進行連接DB並顯示資料
                    int chooseID_index = 個案下拉選單.getSelectedItemPosition();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                            con.run();
                            String userID = "" + UID.get(chooseID_index);
                            String Date = obj1.returnScheduleDate();
                            ArrayList data = con.getschedule(Date, "我要排程工作內容", userID);
                            Log.e("62", "" + data.size());
                            Log.e("63", data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," +
                                    data.get(5));

                            time_view.post(new Runnable() {
                                public void run() {
                                    time_view.setText("" + data.get(2) + "~" + data.get(3));
                                }
                            });

                            caregiver_view.post(new Runnable() {
                                public void run() {
                                    caregiver_view.setText("" + data.get(4));
                                }
                            });

                            服務內容.post(new Runnable() {
                                public void run() {
                                    String work;
                                    work = "" + data.get(5);
                                    work = work.replace("、", "\n");
                                    服務內容.setText(work);
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

                            日期.post(new Runnable() {
                                public void run() {
                                    日期.setText(Date);
                                }
                            });

                            備註.post(new Runnable() {
                                public void run() {
                                    備註.setText(("" + data.get(0)));
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
