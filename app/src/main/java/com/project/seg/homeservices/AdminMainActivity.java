package com.project.seg.homeservices;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class AdminMainActivity extends AppCompatActivity {

    DBHandler db = new DBHandler(this);
    TextView username;
    EditText servicename;
    EditText rate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Bundle extras = getIntent().getExtras();
        String email = extras.getString("emailField");
        String password = extras.getString("passwordField");
        username = findViewById(R.id.usernameDisplay);
        username.setText(db.getUsername(email));
        final Admin admin = new Admin(email,username.getText().toString(),password);

        updateServicesList();
    }

    public void createService(View view) {
        servicename=findViewById(R.id.serviceNameField);
        rate = findViewById(R.id.rateField);
        db.addService(servicename.getText().toString(), new Double(rate.getText().toString()));
        //admin.addService(servicename.getText().toString(),new Double(rate.getText().toString()));

        updateServicesList();
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
        ListView list = findViewById(R.id.servicesList);
        list.setAdapter(cursorAdapter);
    }

    public void signOut(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
