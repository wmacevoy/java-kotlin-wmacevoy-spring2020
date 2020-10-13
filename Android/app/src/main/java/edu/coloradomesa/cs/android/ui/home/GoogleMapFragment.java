package edu.coloradomesa.cs.android.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.coloradomesa.cs.android.MapsActivity;
import edu.coloradomesa.cs.android.R;
import edu.coloradomesa.cs.android.ui.dashboard.DashboardViewModel;

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentActivity fragmentActivity;
    private GoogleMapViewModel mGoogleMapViewModel;
    private GoogleMap mGoogleMap;

    @Override
    public void onAttach(Context context) {
        fragmentActivity=(FragmentActivity) context;
        super.onAttach(context);
        MapsActivity.i("attached map fragment");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mGoogleMapViewModel = new ViewModelProvider(this).get(GoogleMapViewModel.class);
        mGoogleMapViewModel.getCenterLatLng().observe(getViewLifecycleOwner(),center -> onMoveTo(center));

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        MapsActivity.i("map inflated");
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentActivity.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        MapsActivity.i("support map fragment is " + (mapFragment != null ? "not null" : "null"));
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        return root;
    }


    public void onMoveTo(LatLng at) {
        if(mGoogleMap != null) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(at));
        }
    }

    LatLng mSydneyLatLng = new LatLng(-34, 151);
    MarkerOptions mSydneyMarkerOptions = new MarkerOptions().position(mSydneyLatLng).title("Marker in Sydney");
    Marker mSydneyMarker;

    LatLng mGJLatLng = new LatLng(39.0+4.0/60, -(108.0+34.0/60.0));
    MarkerOptions mGJMarkerOptions = new MarkerOptions().position(mGJLatLng).title("Marker in Grand Junction");
    Marker mGJMarker;


    public void onMoveToSydney() { onMoveTo(mSydneyLatLng); }

    public void onMoveToGJ() { onMoveTo(mGJLatLng); }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mSydneyMarker=mGoogleMap.addMarker(mSydneyMarkerOptions);
        mGJMarker=mGoogleMap.addMarker(mGJMarkerOptions);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mSydneyLatLng));
    }

}

/*
public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMapViewModel mGoogleMapViewModel;
    private GoogleMap mGoogleMap;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoogleMapViewModel = new ViewModelProvider(this).get(GoogleMapViewModel.class);
        mGoogleMapViewModel.getCenterLatLng().observe(getViewLifecycleOwner(),center -> onMoveTo(center));
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getMapAsync(this);
        return root;
    }
    LatLng mSydneyLatLng = new LatLng(-34, 151);
    MarkerOptions mSydneyMarkerOptions = new MarkerOptions().position(mSydneyLatLng).title("Marker in Sydney");
    Marker mSydneyMarker;

    LatLng mGJLatLng = new LatLng(39.0+4.0/60, -(108.0+34.0/60.0));
    MarkerOptions mGJMarkerOptions = new MarkerOptions().position(mGJLatLng).title("Marker in Grand Junction");
    Marker mGJMarker;

    public void onMoveTo(LatLng at) {
        if(mGoogleMap != null) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(at));
        }
    }

    public void onMoveToSydney() { onMoveTo(mSydneyLatLng); }

    public void onMoveToGJ() { onMoveTo(mGJLatLng); }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mSydneyMarker=mGoogleMap.addMarker(mSydneyMarkerOptions);
        mGJMarker=mGoogleMap.addMarker(mGJMarkerOptions);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mSydneyLatLng));
    }
}

*/
