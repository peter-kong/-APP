package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.mysql.jdbc.StringUtils;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

public class History_Inform_arrange extends AppCompatActivity {
    String[] str = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題

        setContentView(R.layout.activity_history_inform_arrange);
        final TextView time_view = (TextView) findViewById(R.id.時間顯示);
        final TextView caregiver_view = (TextView) findViewById(R.id.照服員名稱);
        final TextView work_view = (TextView) findViewById(R.id.個人資料);
        final TextView 日期 = (TextView) findViewById(R.id.歷史照服時間);
        final TextView 完成度 = (TextView)findViewById(R.id.完成度);
        final Spinner 日期下拉選單 = (Spinner) findViewById(R.id.日期下拉選單);
        //取全域的歷史日期的資料
        GlobalVariable_Account obj1 = (GlobalVariable_Account) getApplicationContext();
        ArrayList Historydate = obj1.returnDate();
        String[] 歷史日期 = new String[Historydate.size()];
        Historydate.toArray(歷史日期);
        //將所有日期資料放入spinner中
        ArrayAdapter datelist = new ArrayAdapter(History_Inform_arrange.this, R.layout.myspinner, 歷史日期);
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
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        String user帳號  = obj.returnAcc();
                        String UID = con.get_ID(user帳號,"我要userID");
                        ArrayList data = con.getschedule(chooseDate, "我要排程工作內容", UID);
                        if(data.size() == 0) {
                            for (int i = 0; i < 5; i++) {
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

                        完成度.post(new Runnable() {
                            public void run() {
                                String work;
                                work = "" + data.get(1);
                                work = work.replace("、", "\n");
                                完成度.setText(work);
                            }
                        });
                        日期.post(new Runnable() {
                            public void run() {
                                日期.setText("照服時間");
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


        /*
        new Thread(new Runnable() {
            @Override
            public void run() {

                日期下拉選單.setOnItemClickListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String  chooseDate = 日期下拉選單.getSelectedItem().toString();
                        GlobalVariable_Account 選擇歷史日期保存 = (GlobalVariable_Account)getApplicationContext();
                        選擇歷史日期保存.setchooseDate(chooseDate);
                        Intent intent = new Intent();
                        intent.setClass(History_Inform_arrange.this, History_Inform_arrange.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });


                String NeedDate;
                ArrayList data = con.getschedule(NeedDate, "我要上次工作內容", user帳號);

                time_view.post(new Runnable() {
                    public void run() {
                        time_view.setText("" + data.get(0) + "~" + data.get(1));
                    }
                });
                caregiver_view.post(new Runnable() {
                    public void run() {
                        caregiver_view.setText("" + data.get(2));
                    }
                });

                work_view.post(new Runnable() {
                    public void run() {
                        String work;
                        work = "" + data.get(3);
                        work = work.replace("、", "\n");
                        work_view.setText(work);
                    }
                });
                日期.post(new Runnable() {
                    public void run() {
                        日期.setText("照服時間:" + data.get(4));
                    }


                });


            }
        }).start();

         */
    }
}