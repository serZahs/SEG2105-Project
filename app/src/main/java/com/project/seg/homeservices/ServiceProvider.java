package com.project.seg.homeservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class ServiceProvider extends AppCompatActivity {
    DBHandler myDBHandler;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);
        editText = (EditText) findViewById(R.id.editText);
        myDBHandler = new DBHandler(this);
        displayUsername();
    }

    private String getUsernameThruCursor() {

        Cursor data = myDBHandler.getUsername();
        return (data.getString(1));
    }

    public void displayUsername() {

        editText = (EditText) findViewById(R.id.textView4);
        editText.setText(editText.getText() + getUsernameThruCursor() + " to our services!");
    }
}
