package com.project.seg.homeservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ServicesListCustomAdapter extends ArrayAdapter {
    private final Context context;
    private final String[] myServices;
    private final String[] rateHour;

    public ServicesListCustomAdapter(Context context, String[] choreList,String[] rateHour)
    { super(context, R.layout.service_item_layout, choreList);
        this.context = context;
        this.myServices = choreList;
        this.rateHour=rateHour;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.service_item_layout, parent, false);
        TextView serviceNameTextField = (TextView) rowView.findViewById(R.id.serviceNameTextView);
        TextView rateHourTextField = (TextView) rowView.findViewById(R.id.serviceRate_HourView);

        serviceNameTextField.setText(myServices[position]);
        rateHourTextField.setText("CAD"+rateHour[position]);

        return rowView;

    }
}
