package com.example.thematic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Frag_work_report extends Fragment {


    public Frag_work_report() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work_report, container, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String myarg = arguments.getString("myarg");
//            Toast.makeText(getContext(), myarg, Toast.LENGTH_LONG)
//                    .show();
        }
        return rootView;
    }
}


