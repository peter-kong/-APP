package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Recent_Inform_arrange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_inform_arrange);
        getSupportActionBar().hide(); //隱藏標題
        final TextView time_view = (TextView) findViewById(R.id.時間顯示);
        final TextView caregiver_view = (TextView) findViewById(R.id.照服員名稱);
        final TextView work_view = (TextView) findViewById(R.id.個人資料);
        final TextView 完成度 = (TextView)findViewById(R.id.完成度);
        final TextView 日期 = (TextView)findViewById(R.id.最近照服時間);
        final TextView 備註 = (TextView)findViewById(R.id.備註顯示);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //取得本日的日期
                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                con.run();

                //獲得全域中的帳號
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                String user帳號 = obj.returnAcc();
                String Date = obj.returnScheduleDate();
                //取得最近一次的服務內容(包含時間、照服員、服務內容)
                String UID = con.get_ID(user帳號,"我要userID");
                ArrayList data = con.getschedule(Date, "我要排程工作內容", UID);
                if (data.size() == 0) {
                    for (int i = 0; i < 5; i++) {
                        data.add("");
                    }
                }
                //data.add(data.get(0).toString().subSequence(0,));
                Log.e("獲取結果",data.get(0)+","+data.get(1)+","+data.get(2)+","+data.get(3)+","+data.get(4));
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

                work_view.post(new Runnable() {
                    public void run() {
                        String work;
                        work = "" + data.get(4);
                        work = work.replace("-", "\n");
                        work_view.setText(work);
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
                        日期.setText("照服時間:" + Date);
                    }
                });

                備註.post(new Runnable() {
                    public void run() {備註.setText(""+data.get(0));}
                });
            }
        }).start();
    }
}