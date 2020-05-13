package com.example.thematic;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_manager_user_search extends Fragment {


    public Fragment_manager_user_search() {
        // Required empty public constructor
    }
    Button SearchBtn;
    EditText userID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_manager_user_search, container, false);
        super.onCreate(savedInstanceState);
        SearchBtn = (Button)rootview.findViewById(R.id.查詢);
        userID = (EditText) rootview.findViewById(R.id.輸入個案ID);
        DB(SearchBtn);
        return rootview;
    }
    private void DB(Button SearchBtn){
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
                        GlobalVariable_Account obj = (GlobalVariable_Account)getActivity().getApplicationContext();
                        obj.setAccount(user_Account);
                        Log.e("36","Hi");
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), Personal_data.class);
                        startActivity(intent);
                    }
                }).start();


            }
        });
    }
}

