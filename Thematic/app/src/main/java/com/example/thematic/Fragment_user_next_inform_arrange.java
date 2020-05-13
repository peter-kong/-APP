package com.example.thematic;

import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * @description
 * @author: Created jiangjiwei in 2019-06-28 23:42
 */
public class Fragment_user_next_inform_arrange extends Fragment {

    TextView time_view;
    TextView caregiver_view;
    TextView work_view;
    TextView 日期;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_next_inform_arrange, container, false);
        super.onCreate(savedInstanceState);
        time_view = (TextView) rootView.findViewById(R.id.時間顯示);
        caregiver_view = (TextView) rootView.findViewById(R.id.照服員名稱);
        work_view = (TextView) rootView.findViewById(R.id.個人資料);
        日期 = (TextView)rootView.findViewById(R.id.下次照服時間);
        DB();
        return rootView;
    }

    private void DB(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //取得本日日期
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
                //取得全域變數中的帳號
                GlobalVariable_Account obj = (GlobalVariable_Account)getActivity().getApplicationContext();
                String user帳號  = obj.returnAcc();
                //取得下次服務的資料(包含日期、時間、照服員、服務內容)
                Log.e("Next_Inform_arrange","Start");
                ArrayList data = con.getschedule(Date,"我要下次工作內容",user帳號);
                Log.e("Next_Inform_arrange","End"+data.size());
                if(data.size() == 0){
                    for (int i = 0 ; i < 5 ; i ++) {
                        data.add("無資料");
                    }
                }

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
                        work = work.replace("、","\n");
                        work_view.setText(work);
                    }
                });
                日期.post(new Runnable() {
                    public void run() {
                        日期.setText("下次照服時間:"+data.get(4));
                    }
                });


            }
        }).start();
    }
}
