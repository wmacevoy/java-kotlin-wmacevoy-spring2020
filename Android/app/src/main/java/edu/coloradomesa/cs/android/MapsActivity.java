package edu.coloradomesa.cs.android;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    LatLng mSydneyLatLng = new LatLng(-34, 151);
    MarkerOptions mSydneyMarkerOptions = new MarkerOptions().position(mSydneyLatLng).title("Marker in Sydney");
    Marker mSydneyMarker;

    LatLng mGJLatLng = new LatLng(39.0+4.0/60, -(108.0+34.0/60.0));
    MarkerOptions mGJMarkerOptions = new MarkerOptions().position(mGJLatLng).title("Marker in Grand Junction");
    Marker mGJMarker;
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void onMoveToGJ() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mSydneyMarker=mMap.addMarker(mSydneyMarkerOptions);
        mGJMarker=mMap.addMarker(mGJMarkerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mGJLatLng));
    }
}