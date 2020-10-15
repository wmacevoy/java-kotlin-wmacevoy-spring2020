package edu.coloradomesa.cs.android.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import edu.coloradomesa.cs.android.AppFragment;
import edu.coloradomesa.cs.android.R;

public class NotificationsFragment extends AppFragment {
    View root;
    TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_notifications, container, false);
        textView = root.findViewById(R.id.text_notifications);
        getModel().getNotificationsTextLiveData().observe(getViewLifecycleOwner(),
                text -> textView.setText(text));
        // getState().loadFrom(savedInstanceState);
        getModel().setNotificationsText(getModel().getNotificationsText());
        return root;
    }
}