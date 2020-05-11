package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class caregiver_normal_work_report1 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_normal_work_report1);
        getSupportActionBar().hide(); //隱藏標題
        final Spinner 個案名稱 = (Spinner)findViewById(R.id.個案下拉選單);

        GlobalVariable_Account t = (GlobalVariable_Account)getApplicationContext();
        //t.println();

        ArrayList name = t.returnName();
        //ArrayList id = t.returnUID();
        ArrayList nameid = null;

        /*
        for(int i = 0;i < name.size();i++){
            nameid.add(name.get(i).toString()+id.get(i).toString());
            Log.e("nameid", nameid.get(i).toString());
        }
        */

        for(int i = 0;i < name.size();i++)
            Log.e("name:",name.get(i).toString());

        String[] namestr = new String[name.size()];
        name.toArray(namestr);

        ArrayAdapter datelist = new ArrayAdapter(caregiver_normal_work_report1.this, android.R.layout.simple_spinner_item,namestr);
        datelist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        個案名稱.setAdapter(datelist);




        //日期
        SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
        Date date = new Date();
        String strDate = sdFormat.format(date);

        final TextView 日期 = (TextView)findViewById(R.id.個案日期);
        日期.setText(strDate);


        個案名稱.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被選擇项的值進行連接DB並顯示資料
                String chooseDate = (String) 個案名稱.getSelectedItem();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        String caregiver帳號  = obj.returnAcc();

                        int i = 0;
                        ArrayList 所有當前照護員被照護名字 = obj.returnName();
                        while(!所有當前照護員被照護名字.get(i).equals(chooseDate)){
                            i++;
                        }


                        ArrayList data = con.getcaregiverworkcontent(caregiver帳號,obj.returnUID().get(i).toString());
                        //照服員名字,開始時間,結束時間
                        String Time = data.get(1).toString() + " - " +data.get(2).toString();
                        final TextView workername = (TextView)findViewById(R.id.workername);
                        final TextView Timeview = (TextView)findViewById(R.id.Time);

                        workername.post(new Runnable() {
                            public void run() {
                                workername.setText(data.get(0).toString());
                            }
                        });

                        Timeview.post(new Runnable() {
                            public void run() {
                                Timeview.setText(Time);
                            }
                        });

                    }
                }).start();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("nothingSelected","沒有選則內容");
            }
        });

    }
}
