package com.example.thematic;

import android.app.FragmentTransaction;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.thematic.KeepStateNavigator;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Tab_caregiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_caregiver);
        getSupportActionBar().hide(); //隱藏標題

        NavController navController = Navigation.findNavController(this, R.id.fragment3);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        KeepStateNavigator navigator = new KeepStateNavigator(this, navHostFragment.getChildFragmentManager(), R.id.fragment3);
        navController.getNavigatorProvider().addNavigator(navigator);
        navController.setGraph(R.navigation.care_nav);

        setUpNavBottom(navHostFragment);
    }

    private void setUpNavBottom(NavHostFragment hostFragment) {
       // hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        BottomNavigationView navMenu = findViewById(R.id.nv_care_menu);
        if (hostFragment != null) {
            NavController navController = hostFragment.getNavController();
            NavigationUI.setupWithNavController(navMenu, navController);
        }
    }

}





