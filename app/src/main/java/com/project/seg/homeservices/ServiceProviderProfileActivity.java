package com.project.seg.homeservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ServiceProviderProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);
    }

    public void saveInfo (View view) {
        DBHandler dbHandler = new DBHandler(this);

    }
}
