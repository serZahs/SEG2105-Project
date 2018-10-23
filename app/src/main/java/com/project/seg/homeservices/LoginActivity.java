package com.project.seg.homeservices;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Attempts to login to the app. DBHandler is used to see
     * If the input fields contain values corresponding to an entry
     * * in the database
     * @param view login activity
     */
    public void attemptLogin(View view) {
        DBHandler dbHandler = new DBHandler(this);
    }
}
