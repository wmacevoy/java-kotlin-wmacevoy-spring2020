package edu.coloradomesa.cs.android.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;
public class GoogleMapViewModel extends ViewModel {

    private MutableLiveData<LatLng> mCenterLatLng;
    public LiveData<LatLng> getCenterLatLng() { return mCenterLatLng; }

    public GoogleMapViewModel() {
        mCenterLatLng = new MutableLiveData<>(new LatLng(0,0));
    }
}