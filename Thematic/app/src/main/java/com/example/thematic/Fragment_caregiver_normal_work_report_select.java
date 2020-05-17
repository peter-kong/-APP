package com.example.thematic;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    TextView 月份;
    TextView 日期;
    Button 查詢;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_caregiver_normal_work_report_select, container, false);
        super.onCreate(savedInstanceState);
        月份 = (TextView) rootview.findViewById(R.id.輸入月份);
        日期 = (TextView) rootview.findViewById(R.id.輸入日期);
        查詢 = (Button) rootview.findViewById(R.id.查詢);
        DB(查詢);
        return rootview;
    }

    private  void DB(Button 查詢)
    {
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
                        if (月份.getText().toString().matches("")) {
                            input_month = "0";
                        } else if (月份.getText().toString().matches(" ")) {
                            input_month = "0";
                        } else {
                            input_month = 月份.getText().toString();
                        }
                        if (日期.getText().toString().matches("")) {
                            input_day = "0";
                        } else if (日期.getText().toString().matches(" ")) {
                            input_day = "0";
                        } else {
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
                        Time t = new Time();
                        t.setToNow();
                        int month = t.month + 1;
                        int day = t.monthDay;
                        String now = new String();
                        if (month < 10) {
                            if (day < 10) {
                                now = "0" + month + "0" + day;
                            } else {
                                now = "0" + month + day;
                            }
                        } else {
                            if (day < 10) {
                                now = month + "0" + day;
                            } else {
                                now = "" + month + day;
                            }
                        }
                        ArrayList 前五次日期 = new ArrayList();
                        GlobalVariable_Account obj = (GlobalVariable_Account) getActivity().getApplicationContext();
                        String 帳號 = obj.returnAcc();
                        Log.e("now",""+now);
                        int check = 0;
/*
                        前五次日期 = con.get_recent_date(now, "我要前五次的日期",帳號);

                        Log.e("123",前五次日期.get(0)+","+前五次日期.get(1)+","+前五次日期.get(2)+","+前五次日期.get(3));
                        for (int i = 0; i < 前五次日期.size(); i++) {
                            if (Date.equals("" + 前五次日期.get(i))) {
                                check = 1;
                                break;
                            }
                        }
                        Log.e("前五次日期數量",""+前五次日期.size()+","+check);
*/
                        if (check == 1) {
                            String id = con.get_ID(帳號,"我要caregiverID");
                            ArrayList UID = con.GetUserUID_List(id,Date, "我要caregiver當日的UID");
                            ArrayList Name = con.GetName_List(UID);
                            obj.setUID(UID);
                            obj.setName(Name);
                            obj.setScheduleDate(Date);
                            Log.e("NAME","獲取資料成功"+Date);
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), caregiver_normal_work_report.class);
                            startActivity(intent);
                        } else {
                            Log.e("沒有資料","");
                            obj.setUID(new ArrayList());
                            obj.setScheduleDate("無法維護");
                            obj.setName(new ArrayList());
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), caregiver_normal_work_report.class);
                            startActivity(intent);
                        }

                    }
                }).start();
            }
        });
    }
}
