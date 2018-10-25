package com.project.seg.homeservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
public class CreateAccountActivity extends AppCompatActivity {

    EditText email, username, password;
    Button selectType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = findViewById(R.id.emailField);
        username = findViewById(R.id.usernameField);
        password = findViewById(R.id.passwordField);
        selectType = findViewById(R.id.selectTypeButton);

        selectType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to be implemented
            }
        });

        email.setText(getIntent().getStringExtra("emailField"));
    }

    /**
     * Attempsts to create new account using the inputs from the edittexts. If the email is already in
     * use, the account is not created and a message is printed. Otherwise, the account is created and
     * the user is notified.
     *
     * @param view current view
     */
    public void attemptCreateAccount(View view) {
        DBHandler db = new DBHandler(this);

        if (!db.createUser(email.getText().toString(), username.getText().toString(),
                password.getText().toString(), DBHandler.DATABASE_TYPE_HOME_OWNER)) {
            if (!db.isValidEmail((email.getText().toString())))
                Toast.makeText(getApplicationContext(), "invalid email input", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "email has already been used", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "account has been created", Toast.LENGTH_LONG).show();
            finish(); // activity is finished
        }
    }

    private void selectUserType() {
        // implement later
    }
}
