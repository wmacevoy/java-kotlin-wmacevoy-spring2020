package edu.coloradomesa.cs.android;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

public class ConstLatLng implements Comparable<ConstLatLng> {
    private Gson gson = new Gson();
    public static double normalizeAngle(double angle) {
        angle = Math.IEEEremainder(angle,360.0);
        if (angle > 180.0) { angle -= 360.0; }
        if (angle <= -180.0) { angle += 360.0; }
        return angle;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private double latitude;
    private double longitude;

    public ConstLatLng(double latitude, double longitude) {
        this.latitude = normalizeAngle(latitude);
        this.longitude = normalizeAngle(longitude);
    }

    public ConstLatLng(LatLng latLng) {
        this(latLng.latitude,latLng.longitude);
    }


    ConstLatLng(String json) {
        LatLng latLng = gson.fromJson(json,LatLng.class);
        this.latitude = normalizeAngle(latLng.latitude);
        this.longitude = normalizeAngle(latLng.longitude);
    }

    public LatLng getLatLng() {
        return new LatLng(latitude,longitude);
    }

    @Override
    public String toString() {
        return String.format("{\"latitude\":%4.12f,\"longitude\":%4.12f}",latitude,longitude);
    }

    @Override
    public int compareTo(ConstLatLng to) {
        int cmp;
        cmp = Double.compare(latitude, to.latitude);
        if (cmp != 0) {
            return cmp;
        }
        cmp = Double.compare(longitude, to.longitude);
        if (cmp != 0) {
            return cmp;
        }
        return 0;
    }

    @Override
    public boolean equals(Object to) {
        return compareTo((ConstLatLng) to) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(latitude)+3*Double.hashCode(longitude);
    }

    static private double jsonDouble(JSONObject json, String key, double defaultValue) {
        try {
            return json.getDouble(key);
        } catch (JSONException ex) {
            return defaultValue;
        }
    }

    static private JSONObject jsonObject(String json) {
        try {
            return new JSONObject(json);
        } catch (JSONException ex) {
            return new JSONObject();
        }
    }
}
