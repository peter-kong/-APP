package com.example.thematic;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_manager_people_use_search extends Fragment {

    public Fragment_manager_people_use_search() {
        // Required empty public constructor
    }

    Button Btn_month;
    Button Btn_day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manager_people_use_search, container, false);

        Btn_month = rootView.findViewById(R.id.月人力配置概況);
        Btn_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Month_Condition.class);
                startActivity(intent);
            }
        });

        Btn_day = rootView.findViewById(R.id.日人力配置概況);
        Btn_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),Day_Condition.class);
                startActivity(intent);
            }
        });

        return rootView;

    }
}

