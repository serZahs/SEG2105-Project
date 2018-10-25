package com.project.seg.homeservices;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Accounts.db";

    private static final String TABLE_USERS = "allAccountsInfo";

    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_USERNAME = "USERNAME";
    private static final String COLUMN_PASSWORD = "PASSWORD";
    private static final String COLUMN_USER_TYPE = "USERTYPE";

    public static final String DATABASE_TYPE_ADMIN = "ADMIN";
    public static final String DATABASE_TYPE_HOME_OWNER = "HOMEOWNER";
    public static final String DATABASE_TYPE_SERVICE_PROVIDER = "SERVICEPROVIDER";

    private static final String DATABASE_CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS
            + "(" + COLUMN_EMAIL + " TEXT UNIQUE PRIMARY KEY,"
            + COLUMN_USERNAME + " TEXT UNIQUE,"
            + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_USER_TYPE + " TEXT)";

    private static final String TABLE_SERVICES = "allServicesInfo";

    private static final String COLUMN_SERVICE_NAME = "SERVICE";
    private static final String COLUMN_SERVICE_RATE = "RATE";

    private static final String DATABASE_CREATE_SERVICE_TABLE = "CREATE TABLE " + TABLE_SERVICES
            + "(" + COLUMN_SERVICE_NAME
            + " TEXT UNIQUE PRIMARY KEY,"
            + COLUMN_SERVICE_RATE + " DOUBLE)";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_USER_TABLE);
        db.execSQL(DATABASE_CREATE_SERVICE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        onCreate(db);
    }

    // Functions pertaining to the users database

    /**
     * Checks if the email input is of the correct format. That is to say
     * it cannot contain special characters and must have an
     * @ and a .com or .ca at the end. In addition, the local name must be at
     * least 6 characters in length.
     *
     * @param email email input field
     * @return boolean whether or not the email is valid format
     */
    public boolean isValidEmail(String email) {
        char currentChar = ' ';
        int atIndex, dotIndex;
        String splitStringLocal, splitStringDomain, splitStringEnd;

        atIndex = dotIndex = -1;

        /** Iterates through the email string, recording the first instance of @ and last instance
         *  of dot. Also checks if any special character is present.
         */
        for (int i = 0; i < email.length(); i++) {
            currentChar = email.charAt(i);

            if (!Character.isDigit(currentChar) && !Character.isLetter(currentChar)) {
                if (currentChar == '@' && atIndex == -1)
                    atIndex = i;
                else if (currentChar == '.' && atIndex != -1)
                    dotIndex = i;
                else
                    return false;
            }
        }

        if (atIndex == -1 || atIndex < 6) // checks if @ is present and if it is correct length
            return false;

        if (dotIndex == -1) // checks if . is present after the @
            return false;

        /** seperates the email into substrings as follows:
         *  splitStringLocal@splitStringDomain.splitStringEnd
         */
        splitStringLocal = email.substring(0, atIndex - 1);
        splitStringDomain = email.substring(atIndex + 1, dotIndex - 1);
        splitStringEnd = email.substring(dotIndex + 1);

        // returns false if first char is .
        if (splitStringLocal.charAt(0) == '0')
            return false;

        // iterates through domain, checking .'s are present
        for (int i = 0; i < splitStringDomain.length(); i++) {
            currentChar = email.charAt(i);

            if (!Character.isDigit(currentChar) && !Character.isLetter(currentChar))
                return false;
        }

        // return false if the end of the email is not ".ca" or ".com"
        if (!splitStringEnd.equals("com") && !splitStringEnd.equals("ca"))
            return false;

        return true;
    }

    /**
     * Checks if email is valid and then checks if the email and password are present in
     * the database. False is returned if no, true is return if yes.
     *
     * @param email email input field
     * @param password password email field
     * @return boolean whether or not a user inputs were valid
     */
    public boolean isValidUser(String email, String password) {
        if (!isValidEmail(email)) // immediately return false if invalid email
            return false;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT EMAIL, PASSWORD FROM " + TABLE_USERS + " WHERE EMAIL = \""
                + email + "\" AND PASSWORD = \"" + password + "\"";
        Cursor cursor = db.rawQuery(query, null);

        // If the returned set is empty, return false
        if (!cursor.moveToFirst())
            return false;

        return true;
    }

    /**
     * Creates a user after checking if the email is valid and the username is not taken.
     * note: type checker will be made later
     *
     * @param email email input field
     * @param username username input field
     * @param password password input field
     * @param type type selector (not yet implemented)
     * @return boolean whether or not the user was created (for validity)
     */
    public boolean createUser(String email, String username, String password, String type) {
        if (!isValidEmail(email)) // immediately return false if invalid email
            return false;

        SQLiteDatabase db = this.getWritableDatabase();

        /** The following two queries are used to check if the username and email are
         * already associated with an account in the table
         */
        String usernameCheck = "SELECT USERNAME FROM " + TABLE_USERS + " WHERE USERNAME = \""
                + username + "\"";

        String emailCheck = "SELECT EMAIL FROM " + TABLE_USERS + " WHERE EMAIL = \""
                + email + "\"";

        Cursor checkCursor = db.rawQuery(usernameCheck, null);

        if (checkCursor.moveToFirst()) // if the username is used return false
            return false;

        checkCursor = db.rawQuery(emailCheck, null);

        if (checkCursor.moveToFirst()) // if the email is already used return false
            return false;

        // add account to table if the account is valid
        ContentValues values = new ContentValues();

        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_USER_TYPE, DATABASE_TYPE_HOME_OWNER);

        db.insert(TABLE_USERS, null, values);
        db.close();

        return true;
    }

    /**
     * After validation that an email corresponds to an account, the user is returned
     *
     * @param email email input field
     * @return User the user corresponding to the email
     */
    public User getUser(String email) {
        String query = "SELECT USERNAME FROM " + TABLE_USERS + " WHERE EMAIL = \"" + email + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor entryCursor = db.rawQuery(query, null);

        String userEmail = entryCursor.getString(0);
        String userName = entryCursor.getString(1);
        String userPassword = entryCursor.getString(2);
        String userType = entryCursor.getString(3);

        User user = null;

        switch (userType) {
            case DATABASE_TYPE_ADMIN:
                user = new Admin(userEmail, userName, userPassword);
                break;

            case DATABASE_TYPE_HOME_OWNER:
                user = new HomeOwner(userEmail, userName, userPassword);

            default:
                user = new ServiceProvider(userEmail, userName, userPassword);
        }

        return user;
    }

    // Functions pertaining to the services database

    /**
     * Checks if the service input is valid. (not sure how to implement choosing yet).
     * If the service is equal to any of the possible services detailed in the admin class,
     * true is returned. Otherwise false is returned.
     *
     * @param service service input
     * @return boolean whether or not the service name is valid
     */
    public boolean isValidService(String service) {
        for (int possibleService = 0; possibleService < Admin.SERVICES_OFFERED.length;
             possibleService++)
            if (service.equals(Admin.SERVICES_OFFERED[possibleService]))
                return true;

        return false;
    }

    /**
     * Simply checks if a rate is valid or not. 0 == free
     *
     * @param rate rate per hour of pay for service
     * @return boolean whether or not the rate is valid
     */
    public boolean isValidRate(double rate) {
        if (rate < 0)
            return false;

        return true;
    }

    /**
     * Attempts to add a service to the list of services. If it is already present in the service database
     * then false is returned. If it is not a valid service, false is returned. Otherwise, it is added to the
     * database.
     *
     * @param service
     * @param rate
     * @return
     */
    public boolean addService(String service, double rate) {
        if (!isValidService(service) || !isValidRate(rate)) // if either entry is not valid, return false
            return false;

        SQLiteDatabase db = this.getReadableDatabase();
        // query to check if the service is already added
        String serviceCheck = "SELECT SERVICE FROM " + COLUMN_SERVICE_NAME + " WHERE SERVICE = \""
                + service + "\"";
        Cursor checkCursor = db.rawQuery(serviceCheck, null);

        // the service was already in the set
        if (checkCursor.moveToFirst())
            return false;

        return true;
    }
}
