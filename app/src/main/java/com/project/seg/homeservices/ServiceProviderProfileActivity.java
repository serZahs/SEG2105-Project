package com.project.seg.homeservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ServiceProviderProfileActivity extends AppCompatActivity {

    String email;
    EditText addressField;
    String address;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);

        db = new DBHandler(this);
        email = getIntent().getStringExtra("emailField");

        addressField = findViewById(R.id.addressField);
        address = addressField.getText().toString();
        addressField.setText(db.getAddress(email));
    }

    public void saveInfo (View view) {
        db.updateAddress(email, address);

    }
}
