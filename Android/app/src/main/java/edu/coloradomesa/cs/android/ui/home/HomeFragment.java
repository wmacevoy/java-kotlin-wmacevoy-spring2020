package edu.coloradomesa.cs.android.ui.home;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.coloradomesa.cs.android.App;
import edu.coloradomesa.cs.android.AppFragment;
import edu.coloradomesa.cs.android.ConstLatLng;
import edu.coloradomesa.cs.android.R;

public class HomeFragment extends AppFragment implements OnMapReadyCallback {
    private GoogleMap map;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        App.i("HomeFragment.onCreateView(...)");
        getModel().getCenterLiveData().observe(getViewLifecycleOwner(),
                center -> moveTo(center));
        // getState().loadFrom(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        // https://developers.google.com/maps/documentation/android-sdk/map
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();

        getApp().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.home_layout, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);
        return root;
    }


    public void moveTo(ConstLatLng at) {
        if (map != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLng(at.getLatLng());
            map.moveCamera(update);
        }
    }

    MarkerOptions getSydneyMarkerOptions() {
        return ConstLatLng.SYDNEY_NSW_AUSTRALIA.getMarkerOptions().title("Sydney");
    }

    MarkerOptions getGJMarkerOptions() {
        return ConstLatLng.GRAND_JUNCTION_CO_USA.getMarkerOptions().title("Grand Junction");
    }

    @Override
    public void onMapReady(GoogleMap map) {
        App.i("HomeFragment.onMapReady(...)");
        this.map = map;
        this.map.addMarker(getSydneyMarkerOptions());
        this.map.addMarker(getGJMarkerOptions());
        moveTo(getModel().getCenter());
    }

}
