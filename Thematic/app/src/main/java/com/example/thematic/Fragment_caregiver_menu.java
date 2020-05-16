package com.example.thematic;


import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_caregiver_menu extends Fragment {


    public Fragment_caregiver_menu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.fragment_caregiver_menu, container, false);
        super.onCreate(savedInstanceState);
        DB();
        return rootview;
    }

    private void DB(){
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
                tmp2.println();
                // Log.e("tmpreturn", tmp.returnUID().get(0).toString());
            }
        }).start();
    }
}
