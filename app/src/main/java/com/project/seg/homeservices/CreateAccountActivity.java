package com.project.seg.homeservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
public class CreateAccountActivity extends AppCompatActivity {

    EditText email, username, password;
    RadioGroup typeSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = findViewById(R.id.emailField);
        username = findViewById(R.id.usernameField);
        password = findViewById(R.id.passwordField);
        typeSelector = findViewById(R.id.typeSelector);

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
        int selectedID = typeSelector.getCheckedRadioButtonId();
        String type = "";

        // uses radio button group to determine which type was selected
        switch (selectedID) {
            case R.id.typeSelectorAdmin:
                type = DBHandler.DATABASE_TYPE_ADMIN;
                break;

            case R.id.typeSelectorHomeOwner:
                type = DBHandler.DATABASE_TYPE_HOME_OWNER;
                break;

            default:
                type = DBHandler.DATABASE_TYPE_SERVICE_PROVIDER;
                break;
        }

        Toast.makeText(getApplicationContext(), type, Toast.LENGTH_SHORT).show();

        if (!db.createUser(email.getText().toString(), username.getText().toString(),
                password.getText().toString(), type)) {
            if (!db.isValidEmail((email.getText().toString())))
                Toast.makeText(getApplicationContext(), "invalid email input", Toast.LENGTH_SHORT).show();
            else
                if (type.equals(DBHandler.DATABASE_TYPE_ADMIN) && !db.alreadyExistsAdmin())
                    Toast.makeText(getApplicationContext(), "admin account already exists", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "email has already been used", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "account has been created", Toast.LENGTH_SHORT).show();
            finish(); // activity is finished
        }
    }

}
