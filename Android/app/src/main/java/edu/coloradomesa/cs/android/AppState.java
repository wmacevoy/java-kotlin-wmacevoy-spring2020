package edu.coloradomesa.cs.android;

import android.content.Context;
import android.content.RestrictionEntry;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

public class AppState {
    private SharedPreferences mSharedPreferences;
    private Resources mResources;
    private ConstLatLng mCenter = new ConstLatLng(0.0,0.0);
    ConstLatLng getCenter() { return mCenter; }
    void setCenter(ConstLatLng center) {
        mCenter = center;
    }

    AppState(Context context) {
        this(context.getSharedPreferences("app", Context.MODE_PRIVATE),context.getResources());
    }

    AppState(SharedPreferences sharedPreferences,Resources resources) {
        mSharedPreferences=sharedPreferences;
        mResources=resources;
        String defaultCenter = "{\"latitude\":0.0,\"longitude\":0.0}";
        String jsonCenter = sharedPreferences.getString("center",defaultCenter);
        mCenter = new ConstLatLng(jsonCenter);
    }

    void loadFrom(Bundle bundle) {
        mCenter = new ConstLatLng(bundle.getString("center"));
    }

    void saveTo(Bundle bundle) {
        bundle.putString("center",mCenter.toString());
    }

    void save() {
        mSharedPreferences.edit().putString("center",mCenter.toString()).apply();
    }
}
