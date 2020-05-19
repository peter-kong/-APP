package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class manager_caregiver_manage_month_output extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_caregiver_manage_month_output);
        getSupportActionBar().hide(); //隱藏標題

        final Spinner 日期spn = (Spinner)findViewById(R.id.日期下拉選單);
        final Spinner 個案spn = (Spinner)findViewById(R.id.個案下拉選單);
        final TextView time_view = (TextView)findViewById(R.id.工作時間);
        final TextView caregiver_view = (TextView)findViewById(R.id.照服員名字);
        final TextView work1_view = (TextView)findViewById(R.id.服務內容顯示);
        final TextView 完成度 = (TextView)findViewById(R.id.完成度);
        final TextView 備註 = (TextView)findViewById(R.id.備註);

        //取得前一個畫面得到的DateList
        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
        ArrayList input_Date = obj.returnDate();
        String[] Date = new String[input_Date.size()];
        input_Date.toArray(Date);

        //將DateList加入spinner中
        ArrayAdapter adapWeekList = new ArrayAdapter(manager_caregiver_manage_month_output.this, R.layout.myspinner, Date);
        adapWeekList.setDropDownViewResource(R.layout.myspinner);
        日期spn.setAdapter(adapWeekList);


        //獲得個案List
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //你想要延遲執行的程式碼
                        ArrayList input_個案 = new ArrayList();
                        Log.e("原始size",obj.returnName().size()+"");
                        input_個案 = obj.returnName();
                        ArrayList check_input = new ArrayList();
                        //多個check_input檢查是否有資料
                        for(int i=0 ; i < input_個案.size();i++){
                            check_input.add(input_個案.get(i)+"");
                        }
                        check_input.add("無資料");

                        String[] 個案名單;
                        if(check_input.size() == 1){
                            個案名單 = new String[check_input.size()];
                            check_input.toArray(個案名單);
                            Log.e("沒資料","456");
                        }
                        else {
                            個案名單 = new String[input_個案.size()];
                            input_個案.toArray(個案名單);
                            Log.e("有資料","123");
                        }

                        ArrayAdapter adapWeekList1 = new ArrayAdapter(manager_caregiver_manage_month_output.this, R.layout.myspinner, 個案名單);
                        adapWeekList1.setDropDownViewResource(R.layout.myspinner);
                        個案spn.setAdapter(adapWeekList1);
                        Log.e("執行一次","");
                    }
                }, 1000);


        日期spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //將確認後的名單加入spinner中
                String chooseDate = (String) 日期spn.getSelectedItem();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        if(chooseDate.equals("無資料")){

                        }
                        else {
                            String chooseName = (String) 個案spn.getSelectedItem();
                            String choose_date = 日期spn.getSelectedItem() + "";
                            GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                            String ID = obj.returnAcc();
                            Log.e("取得ID", "");
                            ArrayList 個案ID = con.GetUserUID_List(ID, chooseDate, "我要UID列表");
                            Log.e("個案ID數量", 個案ID.size() + "");
                            ArrayList 個案名稱 = con.GetName_List(個案ID);
                            obj.setUID(個案ID);
                            obj.setName(個案名稱);
                            Log.e("個案名稱數量", 個案名稱.size() + "");
                            ArrayList input_個案 = 個案名稱;
                            String[] 個案名單1 = new String[input_個案.size()];
                            input_個案.toArray(個案名單1);
                            String check_date = obj.returnScheduleDate();
                        }
                    }
                }).start();
                //獲得個案List
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //你想要延遲執行的程式碼
                        ArrayList input_個案 = new ArrayList();
                        Log.e("原始size",obj.returnName().size()+"");
                        input_個案 = obj.returnName();
                        ArrayList check_input = new ArrayList();
                        //多個check_input檢查是否有資料
                        for(int i=0 ; i < input_個案.size();i++){
                            check_input.add(input_個案.get(i)+"");
                        }
                        check_input.add("無資料");

                        String[] 個案名單;
                        if(check_input.size() == 1){
                            個案名單 = new String[check_input.size()];
                            check_input.toArray(個案名單);
                            Log.e("沒資料","456");
                        }
                        else {
                            個案名單 = new String[input_個案.size()];
                            input_個案.toArray(個案名單);
                            Log.e("有資料","123");
                        }

                        ArrayAdapter adapWeekList1 = new ArrayAdapter(manager_caregiver_manage_month_output.this, R.layout.myspinner, 個案名單);
                        adapWeekList1.setDropDownViewResource(R.layout.myspinner);
                        個案spn.setAdapter(adapWeekList1);
                        Log.e("執行一次","");
                    }
                }, 1000);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("nothingSelected","沒有選擇內容");
            }
        });


        個案spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String chooseName = (String) 個案spn.getSelectedItem();
                if (chooseName.equals("無資料")) {
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                            con.run();
                            String chooseDate = (String) 日期spn.getSelectedItem();
                            int choose_index = 個案spn.getSelectedItemPosition();
                            String choose_data = ""+個案spn.getSelectedItem();
                            Log.e("有資料了", "123");
                            ArrayList UID = obj.returnUID();
                            String CID = obj.returnAcc();
                            //Log.e("HI", "HI");
                            if(choose_data.equals("無資料")){

                            }
                            else {
                                ArrayList output_data = con.getschedule_day_caregiver(chooseDate, "我要日工作內容","" + UID.get(choose_index),CID);
                                //Log.e("獲得資料", output_data.get(0) + "," + output_data.get(1) + "," + output_data.get(2) + "," + output_data.get(3) + "," + output_data.get(4) + "," + output_data.get(5));
                                time_view.post(new Runnable() {
                                    public void run() {
                                        time_view.setText("" + output_data.get(2) + "~" + output_data.get(3));
                                    }
                                });
                                caregiver_view.post(new Runnable() {
                                    public void run() {
                                        caregiver_view.setText("" + output_data.get(4));
                                    }
                                });
                                work1_view.post(new Runnable() {
                                    public void run() {
                                        String work;
                                        work = "" + output_data.get(5);
                                        work = work.replace("-", "\n");
                                        work1_view.setText(work);
                                    }
                                });
                                完成度.post(new Runnable() {
                                    public void run() {
                                        String work;
                                        work = "" + output_data.get(1);
                                        work = work.replace("、", "\n");
                                        完成度.setText(work);
                                    }
                                });
                                備註.post(new Runnable() {
                                    public void run() {
                                        備註.setText(("" + output_data.get(0)).replace("暫無",""));
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("nothingSelected", "沒有選擇內容");
            }
        });
    }
}