package com.project.seg.homeservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        setWelcomeMsg();
    }

    private void setWelcomeMsg() {
        TextView wMsg = findViewById(R.id.welcomeMsg);
        Intent intent = getIntent();
        String username = intent.getExtras().getString("username");
        wMsg.setText("Welcome Admin " + username);
    }
}
