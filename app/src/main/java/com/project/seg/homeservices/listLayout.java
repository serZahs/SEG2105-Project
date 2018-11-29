package com.project.seg.homeservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class listLayout extends ArrayAdapter<String> {

    private Context context;
    private List<String> strings;

    public listLayout(Context context, List<String> strings) {
        super(context, R.layout.display_availabilities, strings);
        this.context = context;
        this.strings = strings;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.display_availabilities, parent, false);
        TextView textViewName = (TextView) view.findViewById(R.id.textView3);
        textViewName.setText(strings.get(position));


        return view;

    }
}
