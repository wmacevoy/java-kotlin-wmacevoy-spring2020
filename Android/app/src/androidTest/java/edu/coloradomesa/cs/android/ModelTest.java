package edu.coloradomesa.cs.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.SavedStateHandle;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.*;


public class ModelTest {
    Context getContext() { return InstrumentationRegistry.getInstrumentation().getTargetContext(); }
    Model getFakeModel() {
        return new Model(new SavedStateHandle());
    }

    @Test
    public void center() throws Exception {
        Model model = getFakeModel();
        assertEquals(Model.CENTER_DEFAULT, model.getCenter());
        model.setCenter(ConstLatLng.GRAND_JUNCTION_CO_USA);
        setPause();
        assertEquals(ConstLatLng.GRAND_JUNCTION_CO_USA,model.getCenter());
        model.setCenter(ConstLatLng.SYDNEY_NSW_AUSTRALIA);
        setPause();
        assertEquals(ConstLatLng.SYDNEY_NSW_AUSTRALIA,model.getCenter());
    }

    @Test
    public void dashboard() throws Exception {
        Model model = getFakeModel();
        assertEquals(Model.DASHBOARD_DEFAULT, model.getDashboardText());
        model.setDashboardText("not " + Model.DASHBOARD_DEFAULT);
        setPause();
        assertEquals("not " + Model.DASHBOARD_DEFAULT, model.getDashboardText());
    }

    void setPause() throws Exception {
        if (!Looper.getMainLooper().isCurrentThread()) {
            final Boolean[] done = new Boolean[] { false };
            new Handler(Looper.getMainLooper()).post(() -> done[0]=true);
            while (!done[0]) { Thread.sleep(100); }
        }
    }

    @Test
    public void notifications() throws Exception {
        Model model = getFakeModel();
        assertEquals(Model.NOTIFICATIONS_DEFAULT, model.getNotificationsText());
        model.setNotificationsText("not " + Model.NOTIFICATIONS_DEFAULT);
        setPause();
        assertEquals("not " + Model.NOTIFICATIONS_DEFAULT, model.getNotificationsText());
    }
}