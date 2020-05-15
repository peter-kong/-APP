package com.example.thematic;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_User_menu extends Fragment {

    public Fragment_User_menu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_user_menu, container, false);
        super.onCreate(savedInstanceState);
        DB();
        return rootview;
    }

    private void DB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //取得user的歷史日期資料並放入全域變數中
                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                con.run();
                Time t = new Time();
                t.setToNow();
                int month = t.month + 1;
                int day = t.monthDay;
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
                String[] str = null;
                GlobalVariable_Account obj = (GlobalVariable_Account) getActivity().getApplicationContext();
                String user帳號 = obj.returnAcc();
                ArrayList HistoryDate = new ArrayList();
                HistoryDate = con.GetDate(Date, user帳號,"");
                GlobalVariable_Account 歷史日期保存 = (GlobalVariable_Account)getActivity().getApplicationContext();
                歷史日期保存.setDate(HistoryDate);
            }
        }).start();
        GlobalVariable_Account 帳號保存物件 = (GlobalVariable_Account)getActivity().getApplicationContext();
        Log.e("不同 activity test",帳號保存物件.returnAcc());
    }
}
