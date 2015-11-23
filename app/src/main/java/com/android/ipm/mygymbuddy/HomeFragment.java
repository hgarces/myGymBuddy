package com.android.ipm.mygymbuddy;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.ipm.mygymbuddy.util.MGBUtils;
import com.tyczj.extendedcalendarview.CalendarProvider;
import com.tyczj.extendedcalendarview.Event;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment {
    private String eventTitle;
    private String eventDescr;
    private String eventDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ExtendedCalendarView  calendar = (ExtendedCalendarView) rootView.findViewById(R.id.calendar);

        FloatingActionButton newActivityButton = (FloatingActionButton) rootView.findViewById(R.id.fab_button);
        newActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewActivityFragment fragment = new NewActivityFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, null).addToBackStack(null)
                        .commit();
            }
        });
        addEvent();
        if(getArguments() != null)
            addEvents();
        return rootView;
    }

    private void addEvent() {
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_BLUE);
        values.put(CalendarProvider.DESCRIPTION, "Some Description");
        values.put(CalendarProvider.LOCATION, "Some location");
        values.put(CalendarProvider.EVENT, "Event name");

        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        cal.set(2015, 10, 23, 8, 0);
        int StartDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));
        values.put(CalendarProvider.START, cal.getTimeInMillis());
        values.put(CalendarProvider.START_DAY, StartDayJulian);

        cal.set(2015, 10, 23, 13, 0);
        int endDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.END, cal.getTimeInMillis());
        values.put(CalendarProvider.END_DAY, endDayJulian);

        Uri uri = getActivity().getContentResolver().insert(CalendarProvider.CONTENT_URI, values);

    }

    public void addEvents() {
        getBudleArguments();
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_BLUE);
        values.put(CalendarProvider.DESCRIPTION, eventDescr);
        values.put(CalendarProvider.EVENT, eventTitle);

        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();

        Toast.makeText(getActivity(), "aqui "+eventDate, Toast.LENGTH_SHORT);
        //int[] t = MGBUtils.date2int(eventDate);
    }

    private void getBudleArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            eventTitle = bundle.getString("Title");
            eventDescr = bundle.getString("Desc");
            eventDate = bundle.getString("Date");
        }

    }
}
