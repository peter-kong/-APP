package com.example.thematic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * @description
 * @author: Created jiangjiwei in 2019-06-28 23:42
 */
public class Fragment_user_history_inform_arrange extends Fragment {

    TextView time_view;
    TextView caregiver_view;
    TextView work_view;
    TextView 日期;
    Spinner 日期下拉選單;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_history_inform_arrange, container, false);
        super.onCreate(savedInstanceState);
        time_view = (TextView) rootView.findViewById(R.id.時間顯示);
        caregiver_view = (TextView) rootView.findViewById(R.id.照服員名稱);
        work_view = (TextView) rootView.findViewById(R.id.個人資料);
        日期 = (TextView) rootView.findViewById(R.id.歷史照服時間);
        日期下拉選單 = (Spinner) rootView.findViewById(R.id.日期下拉選單);
        DB();
        return rootView;
    }

    private void DB(){
        GlobalVariable_Account obj1 = (GlobalVariable_Account) getActivity().getApplicationContext();
        ArrayList Historydate = obj1.returnDate();
        String[] 歷史日期 = new String[Historydate.size()];
        Historydate.toArray(歷史日期);

        //將所有日期資料放入spinner中
        ArrayAdapter datelist = new ArrayAdapter(getActivity(), R.layout.myspinner, 歷史日期);
        datelist.setDropDownViewResource(R.layout.myspinner);
        日期下拉選單.setAdapter(datelist);
        //spinner被選擇值的時候動作
        日期下拉選單.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被選擇项的值進行連接DB並顯示資料
                String chooseDate = (String) 日期下拉選單.getSelectedItem();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        GlobalVariable_Account obj = (GlobalVariable_Account)getActivity().getApplicationContext();
                        String user帳號  = obj.returnAcc();
                        String UID = con.get_ID(user帳號,"我要userID");
                        ArrayList data = con.getschedule_day_people(chooseDate,"我要日工作內容",UID);
                        if(data.size() == 0) {
                            for (int i = 0; i < 7; i++) {
                                data.add("無資料");
                            }
                        }
                        time_view.post(new Runnable() {
                            public void run() {
                                time_view.setText(""+data.get(2)+"~"+data.get(3));
                            }
                        });
                        caregiver_view.post(new Runnable() {
                            public void run() {
                                caregiver_view.setText(""+data.get(4));
                            }
                        });

                        work_view.post(new Runnable() {
                            public void run() {
                                String work;
                                work = ""+data.get(5);
                                work = work.replace("-","\n");
                                work_view.setText(work);
                            }
                        });
                        日期.post(new Runnable() {
                            public void run() {
                                日期.setText("歷史照服時間");
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("nothingSelected","沒有選擇內容");
            }
        });


    }
}
