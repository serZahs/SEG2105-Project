package com.project.seg.homeservices;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Arrays;

public class ServiceProviderMainActivity extends AppCompatActivity {

    DBHandler db;
    String selectedService = null;
    String email;
    ListView serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);

        db = new DBHandler(this);
        email = getIntent().getStringExtra("emailField");
        TextView welcomeMsg = findViewById(R.id.welcomeMsg);
        welcomeMsg.setText("Welcome " + db.getUsername(email));

        serviceList = findViewById(R.id.listOfServices);
        updateServicesList();
        serviceList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        serviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedService = ((TextView)view.findViewById(R.id.serviceName)).getText().toString();
            }
        });


    }

    public void editProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), ServiceProviderProfileActivity.class);
        intent.putExtra("emailField", email);
        startActivity(intent);
    }

    public void addServiceToProfile(View view) {
        if (selectedService != null && selectedService != "") {
            db.assignSerivce(email, selectedService);
        }
    }

    private void updateServicesList() {
        Cursor cursor = db.getServicesTable();
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


}
