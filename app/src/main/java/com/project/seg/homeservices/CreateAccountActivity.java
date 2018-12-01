package com.project.seg.homeservices;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
public class CreateAccountActivity extends AppCompatActivity {

    private UserRegisterTask mAuthTask = null;

    private DBHandler db;

    private EditText mEmailView, mUsernameView, mPasswordView;
    private RadioGroup mRoleSelector;
    private RadioButton mLastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        db = new DBHandler(this);

        mEmailView = findViewById(R.id.register_email);
        mUsernameView = findViewById(R.id.register_username);
        mPasswordView = findViewById(R.id.register_password);
        mRoleSelector = findViewById(R.id.roleSelector);
        mLastBtn = findViewById(R.id.typeSelectorHomeOwner);
    }

    /**
     * Attempsts to create new account using the inputs from the edittexts. If the email is already in
     * use, the account is not created and a message is printed. Otherwise, the account is created and
     * the user is notified.
     *
     * @param view current view
     */
    public void attemptRegister(View view) {
        if(mAuthTask != null) {
            return;
        }

        mEmailView.setError(null);
        mUsernameView.setError(null);
        mPasswordView.setError(null);
        mLastBtn.setError(null);

        String email = mEmailView.getText().toString();
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        int selectedRoleId = mRoleSelector.getCheckedRadioButtonId();
        String role = null;

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid role
        if (selectedRoleId == -1) {
            mLastBtn.setError(getString(R.string.error_field_required));
            focusView = mRoleSelector;
            cancel = true;
        }


        // uses radio button group to determine which type was selected
        switch (selectedRoleId) {
            case R.id.typeSelectorAdmin:
                role = DBHandler.DATABASE_TYPE_ADMIN;
                break;

            case R.id.typeSelectorHomeOwner:
                role = DBHandler.DATABASE_TYPE_HOME_OWNER;
                break;

            case R.id.typeSelectorServiceProvider:
                role = DBHandler.DATABASE_TYPE_SERVICE_PROVIDER;
                break;
        }

        if(cancel) {
            focusView.requestFocus();
        } else {
            mAuthTask = new UserRegisterTask(email, username, password, role);
            mAuthTask.execute((Void)null);
        }
    }

    private boolean isEmailValid(String email) {
        if (!email.contains("@"))
            return false;
        if (!email.contains("."))
            return false;
        if (email.length() <= 4)
            return false;
        return true;
    }

    private boolean isUsernameValid(String username) {
        if(username.length() <= 2 || username.length() > 20)
            return false;
        return true;
    }

    private boolean isPasswordValid(String password) {
        if (password.length() <= 5 || password.length() > 20)
            return false;
        return true;
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private String mEmail;
        private String mPassword;
        private String mUsername;
        private String mRole;

        private String error;

        UserRegisterTask(String email, String username, String password, String role) {
            mEmail = email;
            mUsername = username;
            mPassword = password;
            mRole = role;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if(mRole.equals(DBHandler.DATABASE_TYPE_ADMIN) && db.adminExists()) {
                error = getString(R.string.error_admin_exists);
                return false;
            } else if(!db.isAvailableEmail(mEmail)) {
                error = getString(R.string.error_email_used);
                return false;
            } else if(!db.isAvailableUsername(mUsername)) {
                error = getString(R.string.error_username_used);
                return false;
            } else {
                boolean newUser = db.createUser(mEmail, mUsername, mPassword, mRole);
                if(!newUser) {
                    error = getString(R.string.error_database);
                    return false;
                } else
                    return true;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if(success) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        R.string.register_success, Toast.LENGTH_SHORT);
                toast.show();
                HelperServices.hideKeyboard(CreateAccountActivity.this);
                finish();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT);
                toast.show();

            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

}
