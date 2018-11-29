package com.project.seg.homeservices;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ServiceProviderProfileActivity extends AppCompatActivity {

    String email;
    EditText addressField;
    EditText phoneField;
    EditText companyField;
    String address;
    String phoneNumber;
    String companyName;
    DBHandler db;
    ListView serviceList;
    String[] listOfAssignedServices;
    CheckBox mon, tue, wed, thu, fri, sat, sun;

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

        /* Checkbox related code */

        mon = findViewById(R.id.cb0);
        tue = findViewById(R.id.cb1);
        wed = findViewById(R.id.cb2);
        thu = findViewById(R.id.cb3);
        fri = findViewById(R.id.cb4);
        sat = findViewById(R.id.cb5);
        sun = findViewById(R.id.cb6);

        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    db.updateAvailabilities(email, DBHandler.COLUMN_MONDAY, "true");
                else
                    db.updateAvailabilities(email, DBHandler.COLUMN_MONDAY, "false");
            }
        });

        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    db.updateAvailabilities(email, DBHandler.COLUMN_TUESDAY, "true");
                else
                    db.updateAvailabilities(email, DBHandler.COLUMN_TUESDAY, "false");
            }
        });

        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    db.updateAvailabilities(email, DBHandler.COLUMN_WEDNESDAY, "true");
                else
                    db.updateAvailabilities(email, DBHandler.COLUMN_WEDNESDAY, "false");
            }
        });

        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    db.updateAvailabilities(email, DBHandler.COLUMN_THURSDAY, "true");
                else
                    db.updateAvailabilities(email, DBHandler.COLUMN_THURSDAY, "false");
            }
        });

        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    db.updateAvailabilities(email, DBHandler.COLUMN_FRIDAY, "true");
                else
                    db.updateAvailabilities(email, DBHandler.COLUMN_FRIDAY, "false");
            }
        });

        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked())
                    db.updateAvailabilities(email, DBHandler.COLUMN_SATURDAY, "true");
                else
                    db.updateAvailabilities(email, DBHandler.COLUMN_SATURDAY, "false");
            }
        });

        sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (sun.isChecked())
                    db.updateAvailabilities(email, DBHandler.COLUMN_SUNDAY, "true");
                else
                    db.updateAvailabilities(email, DBHandler.COLUMN_SUNDAY, "false");
            }
        });

        updateAvailabilityCheckboxes();

        /* Service list related code */

        serviceList = findViewById(R.id.svList);
        updateServicesList();
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

    private void updateServicesList() {
        Cursor cursor = db.getServicesTable(email);
        String[] projections = new String[] {db.COLUMN_ID, db.COLUMN_SERVICE_NAME, db.COLUMN_SERVICE_RATE};
        int[] cols = new int[] {R.id.serviceID, R.id.serviceName, R.id.serviceRate};
        SimpleCursorAdapter cursorAdapter;
        cursorAdapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.service_item_layout,
                cursor,
                projections,
                cols,
                0);
        serviceList.setAdapter(cursorAdapter);
    }

    private void updateAvailabilityCheckboxes() {
        String monVal = db.getAvailabilities(email, DBHandler.COLUMN_MONDAY);
        mon.setChecked(Boolean.parseBoolean(monVal));

        String tueVal = db.getAvailabilities(email, DBHandler.COLUMN_TUESDAY);
        tue.setChecked(Boolean.parseBoolean(tueVal));

        String wedVal = db.getAvailabilities(email, DBHandler.COLUMN_WEDNESDAY);
        wed.setChecked(Boolean.parseBoolean(wedVal));

        String thuVal = db.getAvailabilities(email, DBHandler.COLUMN_THURSDAY);
        thu.setChecked(Boolean.parseBoolean(thuVal));

        String friVal = db.getAvailabilities(email, DBHandler.COLUMN_FRIDAY);
        fri.setChecked(Boolean.parseBoolean(friVal));

        String satVal = db.getAvailabilities(email, DBHandler.COLUMN_SATURDAY);
        sat.setChecked(Boolean.parseBoolean(satVal));

        String sunVal = db.getAvailabilities(email, DBHandler.COLUMN_SUNDAY);
        sun.setChecked(Boolean.parseBoolean(sunVal));
    }
}
