package com.example.thematic;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_manager_caregiver_manage extends Fragment {


    public Fragment_manager_caregiver_manage() {
        // Required empty public constructor
    }

    EditText ID;
    EditText Month;
    EditText Day;
    Button searchBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_manager_caregiver_manage, container, false);
        super.onCreate(savedInstanceState);
        ID = (EditText)rootview.findViewById(R.id.輸入照服員ID);
        Month = (EditText)rootview.findViewById(R.id.輸入月份);
        Day = (EditText)rootview.findViewById(R.id.輸入日期);
        searchBtn = (Button) rootview.findViewById(R.id.查詢);
        DB(searchBtn);
        return rootview;
    }

    private void DB(Button searchBtn)
    {
        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String caregiverID;
                        String inputMonth;
                        String inputDay;
                        if(ID.getText().toString().matches("")){
                            caregiverID = "0";
                        }
                        else if(ID.getText().toString().matches(" ")){
                            caregiverID = "0";
                        }
                        else {
                            caregiverID = ID.getText().toString();
                        }
                        if(Month.getText().toString().matches("")){
                            inputMonth = "0";
                        }
                        else if(Month.getText().toString().matches(" ")){
                            inputMonth = "0";
                        }
                        else {
                            inputMonth = Month.getText().toString();
                        }
                        if(Day.getText().toString().matches("")){
                            inputDay = "0";
                        }
                        else if(Day.getText().toString().matches(" ")){
                            inputDay = "0";
                        }
                        else {
                            inputDay = Day.getText().toString();
                        }

                        String Date = new String();
                        if(Integer.parseInt(inputMonth)<10){
                            if(Integer.parseInt(inputDay)<10){
                                Date = "0"+inputMonth+"0"+inputDay;
                            }
                            else if(Integer.parseInt(inputDay)>=10){
                                Date = "0"+inputMonth+inputDay;
                            }
                        }
                        else if(Integer.parseInt(inputMonth)>=10){
                            if(Integer.parseInt(inputDay)<10){
                                Date = inputMonth+"0"+inputDay;
                            }
                            else if(Integer.parseInt(inputDay)>=10){
                                Date = inputMonth+inputDay;
                            }
                        }
                        ArrayList data = new ArrayList();
                        com.example.mysql_connect.MySQLCon con = new com.example.mysql_connect.MySQLCon();
                        con.run();
                        data = con.getschedule(Date,"我要caregiver工作時間",caregiverID);
                        GlobalVariable_Account obj = (GlobalVariable_Account)getActivity().getApplicationContext();
                        obj.setScheduletime(data,caregiverID,Date);

                        Intent intent = new Intent();
                        intent.setClass(getActivity(), manager_caregiver_manager_output.class);
                        startActivity(intent);
                    }
                }).start();
            }
        });
    }
}




