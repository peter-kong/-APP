package com.example.thematic;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_caregiver_next_work_report extends Fragment {


    public Fragment_caregiver_next_work_report() {
        // Required empty public constructor
    }

    TextView time_view;
    TextView caregiver_view;
    TextView 日期;
    Spinner 個案下拉選單;
    TextView 服務內容;
    TextView 備註;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_caregiver_next_work_report, container, false);
        super.onCreate(savedInstanceState);
        time_view = (TextView)rootview.findViewById(R.id.Time);
        caregiver_view = (TextView) rootview.findViewById(R.id.workername);
        日期 = (TextView) rootview.findViewById(R.id.個案日期);
        個案下拉選單 = (Spinner) rootview.findViewById(R.id.個案下拉選單);
        服務內容 = (TextView)rootview.findViewById(R.id.服務內容);
        備註 = (TextView)rootview.findViewById(R.id.備註  );
        DB();
        return rootview;
    }

    private void DB(){
        GlobalVariable_Account obj1 = (GlobalVariable_Account) getActivity().getApplicationContext();
        ArrayList name = obj1.returnName();
        ArrayList UID = obj1.returnUID();
        String[] 個案名單 = new String[name.size()];
        name.toArray(個案名單);
        //將所有日期資料放入spinner中

        ArrayAdapter datelist = new ArrayAdapter(getActivity().getApplicationContext(),R.layout.myspinner, 個案名單);
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
                        String Date = "";
                        Time t = new Time();
                        t.setToNow();
                        int month = t.month + 1;
                        int day = t.monthDay +1;
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

                        日期.post(new Runnable() {
                            public void run() {
                                String Date1 = "";
                                Time t = new Time();
                                t.setToNow();
                                int month = t.month + 1;
                                int day = t.monthDay +1;
                                if (month < 10) {
                                    if (day < 10) {
                                        Date1 = "0" + month + "0" + day;
                                    } else if (day >= 10) {
                                        Date1 = "0" + month + day;
                                    }
                                } else if (month >= 10) {
                                    if (day < 10) {
                                        Date1 = month + "0" + day;
                                    } else if (day >= 10) {
                                        Date1 = "" + month + day;
                                    }
                                }
                                日期.setText(Date1);
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
