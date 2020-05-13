package com.example.thematic;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_caregiver_history_work_report extends Fragment {


    public Fragment_caregiver_history_work_report() {
        // Required empty public constructor
    }
    TextView 月份;
    TextView 日期;
    Button 查詢;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_caregiver_history_work_report, container, false);
        super.onCreate(savedInstanceState);
        月份 = (TextView) rootview.findViewById(R.id.輸入月份);
        日期 = (TextView) rootview.findViewById(R.id.輸入日期);
        Button search_Btn = (Button)rootview.findViewById(R.id.查詢);
        DB(search_Btn);
        return rootview;
    }

    private void DB(Button search_Btn){
        search_Btn.setOnClickListener(new View.OnClickListener() {
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
                        GlobalVariable_Account obj = (GlobalVariable_Account) getActivity().getApplicationContext();
                        String 帳號 = obj.returnAcc();
                        ArrayList UID = con.getworkreport_UID(Date, "我要caregiver當日的UID",帳號);
                        ArrayList Name = con.getName(UID);
                        obj.setUID(UID);
                        obj.setName(Name);
                        obj.setScheduleDate(Date);
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), caregiver_history_work_report.class);
                        startActivity(intent);

                    }
                }).start();
            }
        });
    }
}