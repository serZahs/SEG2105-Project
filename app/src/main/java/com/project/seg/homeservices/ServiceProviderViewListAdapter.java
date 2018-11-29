package com.project.seg.homeservices;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class ServiceProviderViewListAdapter extends CursorAdapter {

    public ServiceProviderViewListAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.serviceprovider_view_list_item,
                parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView serviceIDText = view.findViewById(R.id.spvServiceID);
        String serviceID = cursor.getString(cursor.getColumnIndexOrThrow(
                DBHandler.COLUMN_ID));
        serviceIDText.setText(serviceID);

        TextView serviceNameText = view.findViewById(R.id.spvServiceName);
        String serviceName = cursor.getString(cursor.getColumnIndexOrThrow(
                DBHandler.COLUMN_SERVICE_NAME));
        serviceNameText.setText(serviceName);

        TextView serviceRateText = view.findViewById(R.id.spvServiceRate);
        String serviceRate = cursor.getString(cursor.getColumnIndexOrThrow(
                DBHandler.COLUMN_SERVICE_RATE));
        serviceRateText.setText(serviceRate);

        Button bookServiceBtn = view.findViewById(R.id.bookBtn);
        bookServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
