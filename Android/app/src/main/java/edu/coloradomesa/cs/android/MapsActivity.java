package edu.coloradomesa.cs.android;

import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import edu.coloradomesa.cs.android.ui.home.GoogleMapFragment;

public class MapsActivity extends AppCompatActivity {
    public static void i(String msg) {
        Log.i(LOG_TAG,msg);
    }
    public static void e(String msg) {
        Log.e(LOG_TAG,msg);
    }
    public static void w(String msg) {
        Log.w(LOG_TAG,msg);
    }
    public static final String LOG_TAG="MapsActivity";
    GoogleMapFragment mGoogleMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Log.i( MapsActivity.LOG_TAG,"onCreate()");
        onCreateBottomNav();
    }

    private void onCreateBottomNav() {
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