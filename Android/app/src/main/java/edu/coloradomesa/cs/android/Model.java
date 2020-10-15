package edu.coloradomesa.cs.android;

import android.os.Looper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.TreeMap;

public class Model extends ViewModel {
    private SavedStateHandle state;

    public Model(SavedStateHandle state)  {
        this.state = state;
    }

    public static final String NOTIFICATIONS_KEY = "notification";
    public static final String NOTIFICATIONS_DEFAULT = "start";
    public MutableLiveData<String> getNotificationsTextLiveData() {
        return state.getLiveData(NOTIFICATIONS_KEY, NOTIFICATIONS_DEFAULT);
    }

    public String getNotificationsText() {
        return getNotificationsTextLiveData().getValue();
    }

    public void setNotificationsText(String text) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            getNotificationsTextLiveData().setValue(text);
        } else {
            getNotificationsTextLiveData().postValue(text);
        }
    }

    public static final String DASHBOARD_KEY = "dashboard";
    public static final String DASHBOARD_DEFAULT = "";
    public MutableLiveData<String> getDashboardTextLiveData() {
        return state.getLiveData(DASHBOARD_KEY, DASHBOARD_DEFAULT);
    }

    public String getDashboardText() {
        return getDashboardTextLiveData().getValue();
    }
    public void setDashboardText(String text) {

        if (Looper.myLooper() == Looper.getMainLooper()) {
            // on UI thread
            getDashboardTextLiveData().setValue(text);
        } else {
            // on some other thread
            getDashboardTextLiveData().postValue(text);
        }
    }

    public static final String CENTER_KEY = "center";
    public static final ConstLatLng CENTER_DEFAULT = ConstLatLng.GRAND_JUNCTION_CO_USA;

    public MutableLiveData<ConstLatLng> getCenterLiveData() {
        return state.getLiveData(CENTER_KEY, CENTER_DEFAULT);
    }

    public ConstLatLng getCenter() {
        ConstLatLng value = getCenterLiveData().getValue();
        App.i("State.getCenter() = " + value);
        return value;
    }
    public void setCenter(ConstLatLng center) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            // on UI thread
            getCenterLiveData().setValue(center);
            App.i("State.setCenter(" +  center + ") set " + (center.equals(getCenter()) ? " ok" : "not ok"));
        } else {
            // on some other thread
            getCenterLiveData().postValue(center);
            App.i("State.setCenter(" +  center + ") post");
        }
    }
}
