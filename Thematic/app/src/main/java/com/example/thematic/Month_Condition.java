package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Month_Condition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_month__condition);
        getSupportActionBar().hide(); //隱藏標題

        final Button count1 = (Button)findViewById(R.id.一月);
        final Button count2 = (Button)findViewById(R.id.二月);
        final Button count3 = (Button)findViewById(R.id.三月);
        final Button count4 = (Button)findViewById(R.id.四月);
        final Button count5 = (Button)findViewById(R.id.五月);
        final Button count6 = (Button)findViewById(R.id.六月);
        final Button count7 = (Button)findViewById(R.id.七月);
        final Button count8 = (Button)findViewById(R.id.八月);
        final Button count9 = (Button)findViewById(R.id.九月);
        final Button count10 = (Button)findViewById(R.id.十月);
        final Button count11 = (Button)findViewById(R.id.十一月);
        final Button count12 = (Button)findViewById(R.id.十二月);


        count1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(1);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(1);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(2);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(2);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(3);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(3);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(4);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(4);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("126","Hi");
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(5);
                Log.e("129","Hi");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(5);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(6);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(6);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(7);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(7);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(8);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(8);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(9);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(99);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(10);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(10);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(11);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        ArrayList 星期 = con.get_week_count(11);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });

        count12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                obj.setMonth(12);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();

                        ArrayList 星期 = con.get_week_count(12);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getApplicationContext();
                        obj.setweek_count(星期);
                        Intent intent = new Intent();
                        intent.setClass(Month_Condition.this, manager_people_use_search_month_read.class);
                        startActivity(intent);
                    }
                }).start();

            }
        });
    }
}
