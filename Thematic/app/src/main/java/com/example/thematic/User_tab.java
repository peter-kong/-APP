package com.example.thematic;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * @author jiangjiwei
 */
public class User_tab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tab);
        getSupportActionBar().hide(); //隱藏標題

        NavController navController = Navigation.findNavController(this, R.id.fragment_care);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_care);
        KeepStateNavigator navigator = new KeepStateNavigator(this, navHostFragment.getChildFragmentManager(), R.id.fragment_care);
        navController.getNavigatorProvider().addNavigator(navigator);
        navController.setGraph(R.navigation.user_nav);

        setUpNavBottom(navHostFragment);
    }

    private void setUpNavBottom(NavHostFragment hostFragment) {
//        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        BottomNavigationView navMenu = findViewById(R.id.nv_care_menu);
        if (hostFragment != null) {
            NavController navController = hostFragment.getNavController();
            NavigationUI.setupWithNavController(navMenu, navController);
        }
    }
}
