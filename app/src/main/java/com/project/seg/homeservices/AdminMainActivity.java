package com.project.seg.homeservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class AdminMainActivity extends AppCompatActivity {

    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        DBHandler db = new DBHandler(this);
        String email = getIntent().getStringExtra("emailField");
        username = findViewById(R.id.usernameDisplay);

        username.setText(username.getText() + db.getUsername(email));
    }

}
