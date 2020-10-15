package edu.coloradomesa.cs.android;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;


import java.io.IOException;
import java.io.Serializable;

public class ConstLatLng implements Comparable<ConstLatLng>, Serializable {
    static final long serialVersionUID = 1L;

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        out.writeDouble(latitude);
        out.writeDouble(longitude);
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException

    {
        latitude=normalizeAngle(in.readDouble());
        longitude=normalizeAngle(in.readDouble());
    }

    public static final ConstLatLng SYDNEY_NSW_AUSTRALIA = new ConstLatLng(-34, 151);
    public static final ConstLatLng GRAND_JUNCTION_CO_USA = new ConstLatLng(39.0+4.0/60, -(108.0+34.0/60.0));

    private static Gson GSON = new Gson();
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
        LatLng latLng = GSON.fromJson(json,LatLng.class);
        this.latitude = normalizeAngle(latLng.latitude);
        this.longitude = normalizeAngle(latLng.longitude);
    }

    public LatLng getLatLng() {
        return new LatLng(latitude,longitude);
    }

    public MarkerOptions getMarkerOptions() {
        return new MarkerOptions().position(getLatLng());
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
}
