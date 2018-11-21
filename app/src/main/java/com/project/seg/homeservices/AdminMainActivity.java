package com.project.seg.homeservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AdminMainActivity extends AppCompatActivity {

    DBHandler db = new DBHandler(this);
    TextView username;
    EditText servicename;
    EditText rate;

    Bundle extras = getIntent().getExtras();
    String email = extras.getString("emailField");
    String password = extras.getString("passwordField");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        username = findViewById(R.id.usernameDisplay);
        username.setText(db.getUsername(email));
        final Admin admin = new Admin(email,username.getText().toString(),password);

        final Button button = findViewById(R.id.createServiceButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                servicename=(EditText)findViewById(R.id.serviceNameField);
                rate = (EditText)findViewById(R.id.rateField);
                admin.addService(servicename.getText().toString(), new Double(rate.getText().toString()));
            }
        });
    }

}
