package com.example.kelvin.mychat.Application;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by kelvin on 3/30/18.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
