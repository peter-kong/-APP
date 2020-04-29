package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class Personal_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_personal_data);
        getSupportActionBar().hide(); //隱藏標題

        final TextView account    = (TextView) findViewById(R.id.個人資料_帳號);
        final TextView name       = (TextView) findViewById(R.id.個人資料_姓名);
        final TextView gender     = (TextView) findViewById(R.id.個人資料_性別);
        final TextView birthday   = (TextView) findViewById(R.id.個人資料_生日);
        final TextView level      = (TextView) findViewById(R.id.個人資料_失能等級);
        final TextView connectnum = (TextView) findViewById(R.id.個人資料_連絡電話);
        final TextView email      = (TextView) findViewById(R.id.個人資料_email);
        final TextView address    = (TextView) findViewById(R.id.個人資料_住址);
        final TextView healthsitu = (TextView) findViewById(R.id.個人資料_健康狀況);
        final TextView personid   = (TextView) findViewById(R.id.個人資料_身分證字號);



        new Thread(new Runnable(){
            @Override
            public void run(){
                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                con.run();
                final String account_get = con.getData();
                Log.v("OK",account_get);
                final String name_get = con.getData();
                Log.v("OK",name_get);
                final String gender_get = con.getData();
                Log.v("OK",gender_get);
                final String birthday_get = con.getData();
                Log.v("OK",birthday_get);
                final String level_get = con.getData();
                Log.v("OK",level_get);
                final String connectnum_get = con.getData();
                Log.v("OK",connectnum_get);
                final String email_get = con.getData();
                Log.v("OK",email_get);
                final String address_get = con.getData();
                Log.v("OK",address_get);
                final String healthsitu_get = con.getData();
                Log.v("OK",healthsitu_get);
                final String personid_get = con.getData();
                Log.v("OK",personid_get);

                account.post(new Runnable() {
                    public void run() {
                        account.setText(account_get);
                    }
                });
                name.post(new Runnable() {
                    public void run() {
                        name.setText(name_get);
                    }
                });
                gender.post(new Runnable() {
                    public void run() {
                        gender.setText(gender_get);
                    }
                });
                birthday.post(new Runnable() {
                    public void run() {
                        birthday.setText(birthday_get);
                    }
                });
                level.post(new Runnable() {
                    public void run() {
                        level.setText(level_get);
                    }
                });
                connectnum.post(new Runnable() {
                    public void run() {
                        connectnum.setText(connectnum_get);
                    }
                });
                email.post(new Runnable() {
                    public void run() {
                        email.setText(email_get);
                    }
                });
                address.post(new Runnable() {
                    public void run() {
                        address.setText(address_get);
                    }
                });
                healthsitu.post(new Runnable() {
                    public void run() {
                        healthsitu.setText(healthsitu_get);
                    }
                });
                personid.post(new Runnable() {
                    public void run() {
                        personid.setText(personid_get);
                    }
                });

            }
        }).start();

    }
}
