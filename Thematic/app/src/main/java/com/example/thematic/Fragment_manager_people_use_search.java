package com.example.thematic;


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

    Button Btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manager_people_use_search, container, false);
        Btn = rootView.findViewById(R.id.月人力配置概況);
        return rootView;
    }


    /*private void toNext() {
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RootFragmentDirections.ActionRootFragmentToSettingsFragment action =
                        RootFragmentDirections.actionRootFragmentToSettingsFragment();

                Navigation.findNavController(Btn)
                        .navigate(action);
            }
        });*/


    }

