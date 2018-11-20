package com.project.seg.homeservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceProviderMainActivity extends AppCompatActivity {

    TextView username;
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);


        String email = getIntent().getStringExtra("emailField");
        final String[] serviceList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out"};
        final String[] rateHour={"1","2","3","4","5"};

        username = findViewById(R.id.usernameDisplay);

        username.setText(username.getText() + db.getUsername(email));
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
