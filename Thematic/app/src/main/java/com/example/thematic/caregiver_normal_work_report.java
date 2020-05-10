package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class caregiver_normal_work_report extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_normal_work_report);

        getSupportActionBar().hide(); //隱藏標題


        //給日期
        String date = "0502";
        //要知道要取幾筆 首先要找到關聯表
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                //連接mysqljava檔
                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                //獲取object id 以找到名字
                final ArrayList objects = con.gotoschedule(date, "want_objects");
                Log.e("objects",""+objects.get(0) +"");
                //獲取有幾個工作
                //獲取工作內容

            }
        }).start();

/*
    //繫結activity user_data_date與amcc
    LinearLayout LL = (LinearLayout)findViewById(R.id.amcc);
    //知道有幾筆後再新增按鍵
    for(int i=0;i<howmany.size();i++)
    {
        String s = howmany.get(i).toString();
        Button userbtn = new Button(this);
        userbtn.setId(i);
        userbtn.setText(s);
        LL.addView(userbtn);


    }*/

    }
}
