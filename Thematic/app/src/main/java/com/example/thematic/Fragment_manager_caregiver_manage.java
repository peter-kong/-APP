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


    Button weekBtn;
    Button dayBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_manager_caregiver_manage, container, false);
        super.onCreate(savedInstanceState);
        Button weekBtn = (Button) rootview.findViewById(R.id.週工作報表查詢);
        Button dayBtn = (Button) rootview.findViewById(R.id.日工作報表查詢);
        DB(weekBtn,dayBtn);
        return rootview;
    }

    private void DB(Button weekBtn,Button dayBtn)
    {
        weekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), manager_caregiver_manage_month_selected.class);
                startActivity(intent);
            }
        });

        dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), manager_caregiver_manage_day_selected.class);
                startActivity(intent);
            }
        });

    }

}




