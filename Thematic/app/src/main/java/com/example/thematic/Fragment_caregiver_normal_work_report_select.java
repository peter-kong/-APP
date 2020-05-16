package com.example.thematic;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_caregiver_normal_work_report_select extends Fragment {


    public Fragment_caregiver_normal_work_report_select() {
        // Required empty public constructor
    }

    Button 明日Btn;
    Button 今日Btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_caregiver_normal_work_report_select, container, false);
        super.onCreate(savedInstanceState);
        明日Btn = (Button) rootview.findViewById(R.id.明日工作報表_care);
        今日Btn = (Button) rootview.findViewById(R.id.本日工作報表_care);
        DB(明日Btn, 今日Btn);
        return rootview;
    }

    private void DB(Button NextpageBtn1, Button NextpageBtn3) {
        NextpageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account tmp = (GlobalVariable_Account) getActivity().getApplicationContext();
                Log.e("Line 37", "Enter");
                //Log.e("明日工作報表",tmp.returnUID().get(0).toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String 照服員帳號 = tmp.returnAcc();
                        GlobalVariable_Account tmp2 = (GlobalVariable_Account) getActivity().getApplicationContext();

                        SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
                        Date date = new Date();
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(date);
                        calendar.add(calendar.DATE, 1);
                        date = calendar.getTime();
                        String strDate = sdFormat.format(date);
                        Log.e("明日", strDate);

                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        Log.e("strDateformenu", strDate);

                        tmp2.setUID(con.getUserUID(照服員帳號, strDate));//找照服員當天要照顧的人的UID
                        tmp2.setName(con.getName(tmp2.returnUID()));//用要照顧的人的UID，並且把UID轉換成人名，再把人名放入全域變數中
                        tmp.setTommorrowoToday(true);
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), caregiver_next_work_report.class);
                        startActivity(intent);
                        tmp2.println();
                        // Log.e("tmpreturn", tmp.returnUID().get(0).toString());
                    }
                }).start();
            }
        });

        NextpageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account tmp = (GlobalVariable_Account) getActivity().getApplicationContext();
                Log.e("Line 37", "Enter");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String 照服員帳號 = tmp.returnAcc();
                        GlobalVariable_Account tmp2 = (GlobalVariable_Account) getActivity().getApplicationContext();

                        SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
                        Date date = new Date();
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(date);
                        date = calendar.getTime();
                        String strDate = sdFormat.format(date);
                        //Log.e("明日", strDate);

                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        Log.e("strDateformenu", strDate);

                        tmp2.setUID(con.getUserUID(照服員帳號, strDate));//找照服員當天要照顧的人的UID
                        tmp2.setName(con.getName(tmp2.returnUID()));//用要照顧的人的UID，並且把UID轉換成人名，再把人名放入全域變數中
                        tmp.setTommorrowoToday(true);
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), caregiver_normal_work_report1.class);
                        startActivity(intent);
                        tmp2.println();
                    }
                }).start();
            }
        });
    }
}
