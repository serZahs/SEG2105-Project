package com.project.seg.homeservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ServiceProviderProfileActivity extends AppCompatActivity {

    String email;
    EditText addressField;
    EditText phoneField;
    EditText companyField;
    String address;
    String phoneNumber;
    String companyName;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_profile);

        db = new DBHandler(this);
        email = getIntent().getStringExtra("emailField");
        addressField = findViewById(R.id.addressField);
        phoneField = findViewById(R.id.phoneField);
        companyField = findViewById(R.id.companyField);

        address = db.getAddress(email);
        if (address != null)
            addressField.setText(address);

        phoneNumber = db.getPhoneNumber(email);
        if (phoneNumber != null)
            phoneField.setText(phoneNumber);

        companyName = db.getCompanyName(email);
        if (companyName != null)
            companyField.setText(companyName);
    }

    public void saveInfo (View view) {
        address = addressField.getText().toString();
        boolean addressSaved = db.setAddress(email, address);

        phoneNumber = phoneField.getText().toString();
        boolean phoneSaved = db.setPhoneNumber(email, phoneNumber);

        companyName = companyField.getText().toString();
        boolean companySaved = db.setCompanyName(email, companyName);


        if (addressSaved && phoneSaved && companySaved) {
            Intent intent = new Intent(getApplicationContext(), ServiceProviderMainActivity.class);
            intent.putExtra("emailField", email);
            startActivity(intent);
        }
    }
}
