package edu.coloradomesa.cs.android;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class AppFragment extends Fragment {
    private App app;

    public App getApp() {
        return app;
    }

    private Model model;

    public Model getModel() {
        if (model == null) {
            model = getApp().getModel();  //new ViewModelProvider(this).get(Model.class);
        }
        return model;
    }

    @Override
    public void onAttach(Context context) {
        App.i("AppFragment.onAttach(Context)");
        super.onAttach(context);
        app =(App) context;
    }
}

