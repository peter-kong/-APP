package com.example.thematic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Service_user_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隱藏標題
        setContentView(R.layout.activity_manager_service_user_data);

        //設定變數 並在layout中找到相對應的東西
        final TextView name = findViewById(R.id.個人資料_姓名);
        final TextView gender = findViewById(R.id.個人資料_性別);
        final TextView birthday = findViewById(R.id.個人資料_生日);
        final TextView connectnum = findViewById(R.id.個人資料_連絡電話);
        final TextView level = findViewById(R.id.個人資料_失能等級);
        final TextView address = findViewById(R.id.個人資料_住址);


        String Account = "a123456789";

        new Thread(new Runnable() {
            @Override
            public void run() {
                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();

                //設定一個物件來裝從getData中拿到的東西
                final String name_get = con.getData(Account, "name_get");
                //final String gender_get = con.getData(Account, "gender_get");
                final String birthday_get = con.getData(Account, "birthday_get");
                final String connectnum_get = con.getData(Account, "connectnum_get");
                final String level_get = con.getData(Account, "level_get");
                final String address_get = con.getData(Account, "address_get");

                //讓上面的變數來拿到從getData獲得的物件

                name.post(new Runnable() {
                    public void run() {
                        name.setText(name_get);
                    }
                });
                //gender db not yet
                /*
                gender.post(new Runnable() {
                    public void run() {
                        gender.setText(gender_get);
                    }
                });
                */
                birthday.post(new Runnable() {
                    public void run() {
                        birthday.setText(birthday_get);
                    }
                });
                connectnum.post(new Runnable() {
                    public void run() {
                        connectnum.setText(connectnum_get);
                    }
                });
                level.post(new Runnable() {
                    public void run() {
                        level.setText(level_get);
                    }
                });
                address.post(new Runnable() {
                    public void run() {
                        address.setText(address_get);
                    }
                });
            }
        }).start();
    }
}
