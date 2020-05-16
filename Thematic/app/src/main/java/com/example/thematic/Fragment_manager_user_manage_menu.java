package com.example.thematic;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_manager_user_manage_menu extends Fragment {


    public Fragment_manager_user_manage_menu() {
        // Required empty public constructor
    }

    Button SearchBtn;

    EditText userID;
    Button weekBtn;
    Button dayBtn;
    Button dataBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_manager_user_manage_menu, container, false);
        super.onCreate(savedInstanceState);
        weekBtn = (Button) rootview.findViewById(R.id.月工作報表查詢);
        dayBtn = (Button) rootview.findViewById(R.id.日工作報表查詢);
        dataBtn = (Button) rootview.findViewById(R.id.個案資料查詢);

        weekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), manager_user_manage_month_selected.class);
                startActivity(intent);
            }
        });

        dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), manager_user_manage_day_selected.class);
                startActivity(intent);
            }
        });

        dataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), manager_user_search.class);
                startActivity(intent);
            }
        });
        return rootview;
    }
}


