package com.project.seg.homeservices;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class ServiceProviderViewActivity extends AppCompatActivity {

    DBHandler db;
    String email;
    Spinner dateSelector;
    ListView listOfServices;
    ServiceProviderViewListAdapter mAdapter;
    RatingBar rating;
    TextView ratingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_view);

        db = new DBHandler(this);
        email = getIntent().getStringExtra("emailField");

        dateSelector = findViewById(R.id.dateSelector);


        Cursor servicesAssignedCursor = db.getServicesTable(email);
        listOfServices = findViewById(R.id.spvServices);
        mAdapter = new ServiceProviderViewListAdapter(this, servicesAssignedCursor);
        listOfServices.setAdapter(mAdapter);

        rating = findViewById(R.id.spRating);
        ratingText = findViewById(R.id.spRatingText);
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                db.addRating(email, rating);
                ratingText.setText("Rating is " + db.getRating(email));
            }
        });

        ratingText.setText("Rating is " + db.getRating(email));
    }
}
