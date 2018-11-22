package com.project.seg.homeservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

public class ServiceProviderMainActivity extends AppCompatActivity {

    DBHandler db = new DBHandler(this);
    TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);

        Bundle extras = getIntent().getExtras();
        String email = extras.getString("emailField");
        String password = extras.getString("passwordField");
        Admin admin = new Admin(db.getAdminEmail(),db.getUsername(db.getAdminEmail()),db.getPassword(db.getAdminEmail()));


        username = findViewById(R.id.usernameDisplay);
        username.setText(username.getText() + db.getUsername(email));
        ServiceProvider serviceProvider = new ServiceProvider(email,username.getText().toString(),password,admin);

        String[] temp1 = db.getService().toArray(new String[0]);
        String[] temp2 = db.getService().toArray(new String[0]);

        final String[] serviceList = temp1;
        final String[] rateHour = temp2;







        ListView listView = (ListView) findViewById(R.id.serviceList);
        ServicesListCustomAdapter adapter = new ServicesListCustomAdapter(this,serviceList,rateHour);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        { @Override
        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

            Intent editorLaunchInterest = new Intent(getApplicationContext(), RateServiceHour.class);
            editorLaunchInterest.putExtra("position",position);
            editorLaunchInterest.putExtra("name",serviceList[position]);
            editorLaunchInterest.putExtra("name",rateHour[position]);

            startActivityForResult(editorLaunchInterest, 0);
        }
        });


    }
}
