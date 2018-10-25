package com.project.seg.homeservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);
    }


    /**
     * Attempts to login to the app. DBHandler is used to see
     * If the input fields contain values corresponding to an entry
     * * in the database
     *
     * @param view login activity
     */
    public void attemptLogin(View view) {
        DBHandler db = new DBHandler(this);

        if (db.isValidUser(email.getText().toString(), password.getText().toString())) {
            Toast.makeText(getApplicationContext(), "authentification successful", Toast.LENGTH_SHORT).show();

            /**
             * Checks type of user that corresponds with the email and the appropriate activity is opened
             */
            User account = db.getUser(email.getText().toString());
            Intent openAccountActivity = null;

            if (account instanceof Admin)
                openAccountActivity = new Intent(getApplicationContext(), AdminMainActivity.class);

//            if (account instanceof HomeOwner)
//                openAccountActivity = new Intent(getApplicationContext(), HomeOwnerMainActivity.class);

//            if (account instanceof ServiceProvider)
//                openAccountActivity = new Intent(getApplicationContext(), ServiceProviderMainActivity.class);

            openAccountActivity.putExtra("usernameField", account.getUsername());
            startActivity(openAccountActivity);
        }
        else
            Toast.makeText(getApplicationContext(), "email or password was invalid", Toast.LENGTH_LONG).show();
    }

    /**
     * Opens create account activity. Passes the email entered to the new activity
     *
     * @param view login activity
     */
    public void createNewAccount(View view) {

        Intent openCreateAccount = new Intent(getApplicationContext(), CreateAccountActivity.class);

        openCreateAccount.putExtra("emailField", email.getText().toString());
        startActivity(openCreateAccount);
    }
}
