package com.example.thematic;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_manager_work_report_search extends Fragment {


    public Fragment_manager_work_report_search() {
        // Required empty public constructor
    }
    TextView 月份;
    TextView 日期;
    Button 查詢;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_manager_work_report_search, container, false);
        super.onCreate(savedInstanceState);
        月份 = (TextView) rootview.findViewById(R.id.輸入月份);
        日期 = (TextView) rootview.findViewById(R.id.輸入日期);
        查詢 = (Button) rootview.findViewById(R.id.查詢);
        DB(查詢);
        return rootview;
    }

    private void DB(Button 查詢){
        查詢.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String Date = new String();
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        String input_month = 月份.getText().toString();
                        String input_day = 日期.getText().toString();
                        if(月份.getText().toString().matches("")){
                            input_month = "0";
                        }
                        else if(月份.getText().toString().matches(" ")){
                            input_month = "0";
                        }
                        else {
                            input_month = 月份.getText().toString();
                        }
                        if(日期.getText().toString().matches("")){
                            input_day = "0";
                        }
                        else if(日期.getText().toString().matches(" ")){
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
                        GlobalVariable_Account obj = (GlobalVariable_Account) getActivity().getApplicationContext();
                        if(Date.equals("00")){
                            ArrayList nullUID = new ArrayList();
                            ArrayList nullName = new ArrayList();
                            obj.setUID(nullUID);
                            obj.setName(nullName);
                        }
                        else {
                            ArrayList UID = con.getSchedule_UID(Date, "我要schedule單日的所有的UID");
                            ArrayList Name = con.getName(UID);
                            obj.setUID(UID);
                            obj.setName(Name);
                        }
                        obj.setScheduleDate(Date);

                        Intent intent = new Intent();
                        intent.setClass(getActivity(), manager_Work_report.class);
                        startActivity(intent);

                    }
                }).start();
            }
        });
    }
}
