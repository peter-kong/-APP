package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class manager_user_search extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_user_search);
        getSupportActionBar().hide(); //隱藏標題
        final Button SearchBtn = (Button)findViewById(R.id.查詢);
        final EditText userID = (EditText) findViewById(R.id.輸入個案ID);
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        String user_Account = new String();
                        String data = new String();
                        if(userID.getText().toString().matches("")){
                            data = "0";
                        }
                        else {
                            data = userID.getText().toString();
                        }
                        data = userID.getText().toString();
                        if(data.equals("0")){
                            user_Account = "0";
                        }
                        else {
                            user_Account = con.getAccount(data, "我要user帳號");
                        }
                        Log.e("33",""+user_Account);
                        GlobalVariable_Account obj = (GlobalVariable_Account) getApplicationContext();
                        obj.setAccount(user_Account);
                        Log.e("36","Hi");
                        Intent intent = new Intent();
                        intent.setClass(manager_user_search.this, Personal_data.class);
                        startActivity(intent);
                    }
                }).start();


            }
        });

    }
}
