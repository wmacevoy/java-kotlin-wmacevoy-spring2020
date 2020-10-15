package edu.coloradomesa.cs.android.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import edu.coloradomesa.cs.android.AppFragment;
import edu.coloradomesa.cs.android.ConstLatLng;
import edu.coloradomesa.cs.android.R;

public class DashboardFragment extends AppFragment {
    View root;
    TextView textView;
    Button sydney;
    Button gj;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        textView = root.findViewById(R.id.text_dashboard);
        sydney = root.findViewById(R.id.sydney_dashboard);
        sydney.setOnClickListener(view -> clickSydney());
        gj = root.findViewById(R.id.gj_dashboard);
        gj.setOnClickListener(view -> clickGJ());
        getModel().getDashboardTextLiveData().observe(getViewLifecycleOwner(),
           text -> textView.setText(text));
        // getState().loadFrom(savedInstanceState);
        getModel().setDashboardText(getModel().getDashboardText());
        return root;
    }

    void clickSydney() {
        getModel().setCenter(ConstLatLng.SYDNEY_NSW_AUSTRALIA);
        getModel().setDashboardText("off to Sydney!");
        getModel().setNotificationsText(getModel().getNotificationsText() + " -> sydney");
    }
    void clickGJ() {
        getModel().setCenter(ConstLatLng.GRAND_JUNCTION_CO_USA);
        getModel().setDashboardText("off to Grand Junction!");
        getModel().setNotificationsText(getModel().getNotificationsText() + " -> gj");
    }

}