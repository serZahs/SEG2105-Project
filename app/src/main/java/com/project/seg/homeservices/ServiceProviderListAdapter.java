package com.project.seg.homeservices;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ServiceProviderListAdapter extends CursorAdapter {

    public ServiceProviderListAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.serviceprovider_list_item,
                parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView userNameText = view.findViewById(R.id.spUserName);
        String userName = cursor.getString(cursor.getColumnIndexOrThrow(DBHandler.COLUMN_USERNAME));
        userNameText.setText(userName);

        TextView servicesText = view.findViewById(R.id.spServices);
        String services = cursor.getString(cursor.getColumnIndexOrThrow(
                DBHandler.COLUMN_SERVICES_ASSIGNED));
        servicesText.setText(services);
    }
}
