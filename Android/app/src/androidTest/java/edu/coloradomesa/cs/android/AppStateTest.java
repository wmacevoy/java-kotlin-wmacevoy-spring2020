package edu.coloradomesa.cs.android;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class AppStateTest {
    Context getContext() { return InstrumentationRegistry.getInstrumentation().getTargetContext(); }

    @Test
    public void center() {
        AppState state = new AppState(getContext());
        ConstLatLng p = new ConstLatLng(1,2);
        state.setCenter(p);
        ConstLatLng q = state.getCenter();
        assertEquals(p,q);

        p = new ConstLatLng(3,4);
        state.setCenter(p);
        q = state.getCenter();
        assertEquals(p,q);
    }

    @Test
    public void save() {
        {
            AppState state = new AppState(getContext());
            ConstLatLng p = new ConstLatLng(1, 2);
            state.setCenter(p);
            state.save();
        }

        {
            AppState state = new AppState(getContext());
            ConstLatLng p = new ConstLatLng(1, 2);
            ConstLatLng q = state.getCenter();
            assertEquals(p,q);
            state.setCenter(new ConstLatLng(3,4));
            // don't save
        }

        {
            AppState state = new AppState(getContext());
            ConstLatLng p = new ConstLatLng(1, 2);
            ConstLatLng q = state.getCenter();
            assertEquals(p,q);
        }
    }
}