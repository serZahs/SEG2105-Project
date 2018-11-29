package com.project.seg.homeservices;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeOwnerMainActivity extends AppCompatActivity {

    DBHandler db;
    String email;
    TextView username;

    private ListView listOfServiceProviders;
    private ServiceProviderListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_main);

        db = new DBHandler(this);
        email = getIntent().getStringExtra("emailField");
        username = findViewById(R.id.usernameDisplay);

        //username.setText(username.getText() + db.getUsername(email));

        Cursor usernameCursor = db.getServiceProviderInfo();
        listOfServiceProviders = findViewById(R.id.spList);
        mAdapter = new ServiceProviderListAdapter(this, usernameCursor);
        listOfServiceProviders.setAdapter(mAdapter);
        listOfServiceProviders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView itemSelected = view.findViewById(R.id.spUserName);
                String selectedEmail = db.getEmail(itemSelected.getText().toString());

                Intent intent = new Intent(getApplicationContext(), ServiceProviderViewActivity.class);
                Bundle extras = new Bundle();
                extras.putString("emailField", selectedEmail);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }

    public void signOut(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
