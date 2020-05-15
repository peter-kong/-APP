package com.example.thematic;


import android.animation.LayoutTransition;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.security.KeyStore;


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
        return rootview;

    }
}
