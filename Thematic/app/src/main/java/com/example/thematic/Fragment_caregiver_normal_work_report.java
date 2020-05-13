package com.example.thematic;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_caregiver_normal_work_report extends Fragment {


    public Fragment_caregiver_normal_work_report() {
        // Required empty public constructor
    }
    Button NextpageBtn1;
    Button NextpageBtn3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_caregiver_normal_work_report, container, false);
        super.onCreate(savedInstanceState);
        NextpageBtn1 = (Button) rootview.findViewById(R.id.明日工作報表_care);
        NextpageBtn3 = (Button) rootview.findViewById(R.id.本日工作報表_care);
        DB(NextpageBtn1,NextpageBtn3);

        return rootview;
    }

    private  void DB(Button NextpageBtn1,Button NextpageBtn3)
    {
        NextpageBtn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GlobalVariable_Account tmp = (GlobalVariable_Account)getActivity().getApplicationContext();
                Log.e("Line 37","Enter");
                //Log.e("明日工作報表",tmp.returnUID().get(0).toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String 照服員帳號 = tmp.returnAcc();
                        GlobalVariable_Account tmp2 = (GlobalVariable_Account)getActivity().getApplicationContext();

                        SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
                        Date date = new Date();
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(date);
                        calendar.add(calendar.DATE, 1);
                        date = calendar.getTime();
                        String strDate = sdFormat.format(date);
                        Log.e("明日",strDate);

                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        Log.e("strDateformenu",strDate);

                        tmp2.setUID(con.getUserUID(照服員帳號,strDate));
                        tmp2.setName(con.getName(tmp2.returnUID()));
                        tmp.setTommorrowoToday(true);
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), caregiver_next_work_report.class);
                        startActivity(intent);
                        tmp2.println();
                        // Log.e("tmpreturn", tmp.returnUID().get(0).toString());
                    }
                }).start();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(tmp.returnUID().size() == 0) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("沒有個資喔!!")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).setNegativeButton("cancel", null).create()
                                    .show();
                        }
                    }
                }, 1000);
            }
        });

        NextpageBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVariable_Account tmp = (GlobalVariable_Account)getActivity().getApplicationContext();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        GlobalVariable_Account tmp = (GlobalVariable_Account)getActivity().getApplicationContext();
                        String 照服員帳號 = tmp.returnAcc();
                        GlobalVariable_Account tmp2 = (GlobalVariable_Account)getActivity().getApplicationContext();

                        //設時間
                        SimpleDateFormat sdFormat = new SimpleDateFormat("MMdd");
                        Date date = new Date();
                        String strDate = sdFormat.format(date);
                        Log.e("本日",strDate);
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        tmp2.setUID(con.getUserUID(照服員帳號,strDate));

                        tmp2.setName(con.getName(tmp2.returnUID()));
                        //tmp2.println();


                        tmp.setTommorrowoToday(false);

                        if(tmp.returnUID().size() != 0) {
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), caregiver_normal_work_report1.class);
                            startActivity(intent);
                        }

                    }
                }).start();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(tmp.returnUID().size() == 0) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("沒有個資喔!!")
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    }).setNegativeButton("cancel", null).create()
                                    .show();
                        }
                    }
                }, 1000);


            }
        });
    }
}
