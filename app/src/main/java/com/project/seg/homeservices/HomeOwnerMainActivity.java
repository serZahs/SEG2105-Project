package com.project.seg.homeservices;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import java.util.*;

public class HomeOwnerMainActivity extends AppCompatActivity {

    DBHandler db;
    String email;
    TextView username;
    ListView serviceList;
    String providertemp;
    TextView temp_provider_name;

    private ListView listOfServiceProviders;
    private ServiceProviderListAdapter mAdapter;
    private ListView dayListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_main);

        db = new DBHandler(this);
        String email = getIntent().getStringExtra("emailField");
        username = findViewById(R.id.usernameDisplay);

        username.setText(username.getText() + db.getUsername(email));

        Cursor usernameCursor = db.getServiceProviderInfo();
        listOfServiceProviders = findViewById(R.id.spList);
        mAdapter = new ServiceProviderListAdapter(this, usernameCursor);
        listOfServiceProviders.setAdapter(mAdapter);


        listOfServiceProviders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    temp_provider_name = view.findViewById(R.id.spUserName);
                    updateServicesList();

            }

        });


    }


    private void updateServicesList() {
        Cursor cursor = db.getServicesTable(db.getEmailByName(temp_provider_name.getText().toString()));

        String[] projections = new String[] {db.COLUMN_ID, db.COLUMN_SERVICE_NAME, db.COLUMN_SERVICE_RATE};
        int[] cols = new int[] {R.id.id,R.id.Service_Text_Field, R.id.available_Date_Field,};
        SimpleCursorAdapter cursorAdapter;
        cursorAdapter = new SimpleCursorAdapter(
                getBaseContext(),
                R.layout.service_offer_layout,
                cursor,
                projections,
                cols,
                0);
        serviceList = new ListView(this);
        setContentView(serviceList);
        serviceList.setAdapter(cursorAdapter);




    }


    public void displayDate(View view) {
        String monVal = db.getAvailabilities(db.getEmailByName(temp_provider_name.getText().toString()),DBHandler.COLUMN_MONDAY);
        String tuesVal = db.getAvailabilities(db.getEmailByName(temp_provider_name.getText().toString()),DBHandler.COLUMN_TUESDAY);
        String wedVal = db.getAvailabilities(db.getEmailByName(temp_provider_name.getText().toString()),DBHandler.COLUMN_WEDNESDAY);
        String thursVal = db.getAvailabilities(db.getEmailByName(temp_provider_name.getText().toString()),DBHandler.COLUMN_THURSDAY);
        String friVal = db.getAvailabilities(db.getEmailByName(temp_provider_name.getText().toString()),DBHandler.COLUMN_FRIDAY);
        String saturVal = db.getAvailabilities(db.getEmailByName(temp_provider_name.getText().toString()),DBHandler.COLUMN_SATURDAY);
        String sunVal = db.getAvailabilities(db.getEmailByName(temp_provider_name.getText().toString()),DBHandler.COLUMN_SUNDAY);
        List<String> strings = new ArrayList<String>();
        if (monVal != null) {
            strings.add("Monday");
        }
        if (tuesVal != null) {
            strings.add("Tuesday");
        }
        if (wedVal != null) {
            strings.add("Wednesday");
        }
        if (thursVal != null) {
            strings.add("Thursday");
        }
        if (friVal != null) {
            strings.add("Friday");
        }
        if (saturVal != null) {
            strings.add("Saturday");
        }
        if (sunVal != null) {
            strings.add("Sunday");
        }
        dayListView = new ListView(this);
        setContentView(dayListView);
        dayListView.setAdapter(new listLayout(this, strings));


    }

    public void displayContact(View view) {


    }
}
