package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class manager_user_manage_month_selected extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_user_manage_month_selected);

        final EditText userID = (EditText) findViewById(R.id.輸入個案ID);
        final TextView 月份 = (TextView) findViewById(R.id.輸入月份);
        final Button 查詢 = (Button) findViewById(R.id.查詢);

        查詢.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String Date = new String();
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        String user_Account = new String();
                        String data = new String();
                        if(userID.getText().toString().matches("")){
                            data = "0";
                        }
                        else if(userID.getText().toString().matches(" ")){
                            data = "0";
                        }
                        else {
                            data = userID.getText().toString();
                        }
                        if(data.equals("0")){
                            user_Account = "0";
                        }
                        else {
                            user_Account = con.getAccount(data, "我要user帳號");
                        }
                        Log.e("33",""+user_Account);
                        GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                        obj.setAccount(user_Account);
                        String input_month;
                        String input_day;
                        if(月份.getText().toString().matches("")){
                            input_month = "0";
                        }
                        else if(月份.getText().toString().matches(" ")){
                            input_month = "0";
                        }
                        else {
                            input_month = 月份.getText().toString();
                        }
                        if (Integer.parseInt(input_month) < 10) {
                            Date = "0"+input_month+"00";
                        } else if (Integer.parseInt(input_month) >= 10) {
                            Date = input_month+"00";
                        }

                        String 帳號 = obj.returnAcc();
                        ArrayList month_date = new ArrayList();
                        month_date = con.GetDate(Date,帳號,"我要本月日期");
                        obj.setDate(month_date);
                        Intent intent = new Intent();
                        intent.setClass(manager_user_manage_month_selected.this, History_Inform_arrange.class);
                        startActivity(intent);

                    }
                }).start();
            }
        });
    }
}
