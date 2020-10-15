package edu.coloradomesa.cs.android;

import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MapsApp extends App {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.i("MapsApp.onCreate(Bundle)");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        onCreateBottomNav();
    }

    private void onCreateBottomNav() {
        App.i("MapsApp.onCreateBottomNav()");
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
}