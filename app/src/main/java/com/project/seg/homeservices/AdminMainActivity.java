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

        final Button button = findViewById(R.id.createServiceButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                servicename=findViewById(R.id.serviceNameField);
                rate = findViewById(R.id.rateField);
                db.addService(servicename.getText().toString(), new Double(rate.getText().toString()));
                admin.addService(servicename.getText().toString(),new Double(rate.getText().toString()));

            }
        });
    }

}
