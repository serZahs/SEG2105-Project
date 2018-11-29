package com.project.seg.homeservices;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeOwnerMainActivity extends AppCompatActivity {

    DBHandler db;

    TextView username;

    private ListView listOfServiceProviders;
    private ServiceProviderListAdapter mAdapter;

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
    }
}
