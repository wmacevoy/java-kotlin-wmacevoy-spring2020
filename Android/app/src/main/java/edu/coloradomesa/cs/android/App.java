package edu.coloradomesa.cs.android;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

public class App extends AppCompatActivity {
    // single global state, hence "static"
    private Model model;
    public Model getModel() { return model; }

    public static final String LOG_TAG = "edu.coloradomesa.cs.android";

    public static void i(String msg) {
        Log.i(LOG_TAG, msg);
    }

    public static void e(String msg) {
        Log.e(LOG_TAG, msg);
    }

    public static void w(String msg) {
        Log.w(LOG_TAG, msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(Model.class);
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        App.i("App.onSaveInstanceState(Bundle)");
        super.onSaveInstanceState(savedInstanceState);
        state.saveTo(savedInstanceState);
    }
    */

    /*(
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        App.i("App.onRestoreInstanceState(Bundle)");
        super.onRestoreInstanceState(savedInstanceState);
        state.loadFrom(savedInstanceState);
    }
    */
}
