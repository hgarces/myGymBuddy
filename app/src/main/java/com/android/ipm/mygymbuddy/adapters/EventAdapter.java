package com.android.ipm.mygymbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.ipm.mygymbuddy.R;
import com.tyczj.extendedcalendarview.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    Context mContext;
    private int layoutResource;

    public EventAdapter(Context context, int resource, ArrayList<Event> objects) {
        super(context, resource, objects);
        mContext = context;
        layoutResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.item_list, null);
        }
        Event e = getItem(position);

        if(e != null) {
            TextView title = (TextView) view.findViewById(R.id.event_title);
            TextView descr = (TextView) view.findViewById(R.id.event_descr);
            TextView date = (TextView) view.findViewById(R.id.event_time);

            if(title != null) title.setText(e.getTitle());
            if(descr != null) descr.setText(e.getDescription());
            if(date != null) date.setText(e.getStartDate("hh:mm") + " - "+ e.getEndDate("hh:mm"));
        }
        return view;
    }
}
