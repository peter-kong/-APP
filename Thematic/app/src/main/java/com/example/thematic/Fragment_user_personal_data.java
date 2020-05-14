package com.example.thematic;

import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * @description
 * @author: Created jiangjiwei in 2019-06-28 23:42
 */
public class Fragment_user_personal_data extends Fragment {

     TextView account;
     TextView name;
     TextView gender;
     TextView birthday;
     TextView level;
     TextView connectnum;
     TextView email;
     TextView address;
     TextView healthsitu;
     TextView personid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_user_personal_data, container, false);
        super.onCreate(savedInstanceState);

        account    = (TextView) rootview.findViewById(R.id.個人資料_帳號);
        name       = (TextView) rootview.findViewById(R.id.個人資料_姓名);
        gender     = (TextView) rootview.findViewById(R.id.個人資料_性別);
        birthday   = (TextView) rootview.findViewById(R.id.個人資料_生日);
        level      = (TextView) rootview.findViewById(R.id.個人資料_失能等級);
        connectnum = (TextView) rootview.findViewById(R.id.個人資料_連絡電話);
        email      = (TextView) rootview.findViewById(R.id.個人資料);
        address    = (TextView) rootview.findViewById(R.id.個人資料_住址);
        healthsitu = (TextView) rootview.findViewById(R.id.個人資料_健康狀況);
        personid   = (TextView) rootview.findViewById(R.id.個人資料_身分證字號);
        DB();
        return rootview;
    }

    private void DB(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                GlobalVariable_Account obj = (GlobalVariable_Account)getActivity().getApplicationContext();
                String Account  = obj.returnAcc();
                com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                con.run();
                Log.e("38","Hi");
                //Log.e("OK","Hello Line 34");
                //con.run();
                //設定一個物件來裝從getData中拿到的東西
                //Log.e("OK","Hello");
                final String account_get = Account;
                Log.e("44","Hi");
                //跟server說OK(?
                //Log.v("OK",account_get);
                final String name_get = con.getData(Account,"name_get");
                //Log.v("OK",name_get);

                final String gender_get = con.getData(Account,"gender_get");
                Log.v("OK",gender_get);

                final String birthday_get = con.getData(Account,"birthday_get");
                //Log.v("OK",birthday_get);
                final String level_get = con.getData(Account,"level_get");
                //Log.v("OK",level_get);
                final String connectnum_get = con.getData(Account,"connectnum_get");
                //Log.v("OK",connectnum_get);
                final String email_get = con.getData(Account,"email_get");
                //Log.v("OK",email_get);
                final String address_get = con.getData(Account,"address_get");
                //Log.v("OK",address_get);
                final String healthsitu_get = con.getData(Account,"healthsitu_get");
                //Log.v("OK",healthsitu_get);
                final String personid_get = con.getData(Account,"personid_get");
                //Log.v("OK",personid_get);
                //讓上面的變數來拿到從getData獲得的物件
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
                //gender db not yet

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
                        level.setText(""+level_get);
                    }
                });
                connectnum.post(new Runnable() {
                    public void run() {
                        connectnum.setText(connectnum_get);
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







