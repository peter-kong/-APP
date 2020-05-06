package com.example.thematic;

import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Next_Inform_arrange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_user_next_inform_arrange);

        final TextView time_view = (TextView) findViewById(R.id.時間顯示);
        final TextView caregiver_view = (TextView) findViewById(R.id.照服員名稱);
        final TextView work_view = (TextView) findViewById(R.id.個人資料);
        final TextView 日期 = (TextView)findViewById(R.id.最近照服時間);

        new Thread(new Runnable() {
            @Override
            public void run() {

                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                con.run();
                Time t = new Time();
                t.setToNow();
                int month = t.month + 1;
                int day = t.monthDay;
                Log.e("next_inform_arrange", "" + month + "," + day + "");
                String Date = "";
                if (month < 10) {
                    if (day < 10) {
                        Date = "0" + month + "0" + day;
                    } else if (day >= 10) {
                        Date = "0" + month + day;
                    }
                } else if (month >= 10) {
                    if (day < 10) {
                        Date = month + "0" + day;
                    } else if (day >= 10) {
                        Date = "" + month + day;
                    }
                }

                GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                String user帳號  = obj.returnAcc();

                ArrayList data = con.getschedule(Date,"我要下次工作內容",user帳號);
                time_view.post(new Runnable() {
                    public void run() {
                        time_view.setText(""+data.get(0)+"~"+data.get(1));
                    }
                });
                caregiver_view.post(new Runnable() {
                    public void run() {
                        caregiver_view.setText(""+data.get(2));
                    }
                });

                work_view.post(new Runnable() {
                    public void run() {
                        String work;
                        work = ""+data.get(3);
                        //work = work.replaceAll(",","\\n");
                        work_view.setText(work);
                    }
                });
                日期.post(new Runnable() {
                    public void run() {
                        日期.setText("下次日期:"+data.get(4));
                    }
                });
            }
        }).start();
    }

}
