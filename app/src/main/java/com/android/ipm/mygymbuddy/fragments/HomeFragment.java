package com.android.ipm.mygymbuddy.fragments;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.ipm.mygymbuddy.R;
import com.android.ipm.mygymbuddy.adapters.EventAdapter;
import com.tyczj.extendedcalendarview.CalendarProvider;
import com.tyczj.extendedcalendarview.Day;
import com.tyczj.extendedcalendarview.Event;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment {
    private String eventTitle;
    private String eventDescr;
    private String eventDate;

    ExtendedCalendarView calendar;
    EventAdapter eventAdapter;
    ListView listView;
    ArrayList itemsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        calendar = (ExtendedCalendarView) rootView.findViewById(R.id.calendar);
        listView = (ListView) rootView.findViewById(R.id.events_listview);
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
        addEvent2();
        if(getArguments() != null)
            addEvents();
        initListener();
        return rootView;
    }

    private void addEvent() {
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_BLUE);
        values.put(CalendarProvider.DESCRIPTION, "Some Description");
        values.put(CalendarProvider.LOCATION, "Some location");
        values.put(CalendarProvider.EVENT, "Eventhhh name");

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

    private void addEvent2() {
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_RED);
        values.put(CalendarProvider.DESCRIPTION, "Some Description");
        values.put(CalendarProvider.LOCATION, "Some location");
        values.put(CalendarProvider.EVENT, "Eventhhh name");

        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        cal.set(2015, 10, 23, 17, 0);
        int StartDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));
        values.put(CalendarProvider.START, cal.getTimeInMillis());
        values.put(CalendarProvider.START_DAY, StartDayJulian);

        cal.set(2015, 10, 23, 19, 0);
        int endDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.END, cal.getTimeInMillis());
        values.put(CalendarProvider.END_DAY, endDayJulian);

        Uri uri = getActivity().getContentResolver().insert(CalendarProvider.CONTENT_URI, values);

    }

    public void addEvents() {
        /*
        getBudleArguments();
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Eventhhh.COLOR_BLUE);
        values.put(CalendarProvider.DESCRIPTION, eventDescr);
        values.put(CalendarProvider.EVENT, eventTitle);

        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();

        Toast.makeText(getActivity(), "aqui "+eventDate, Toast.LENGTH_SHORT);
        //int[] t = MGBUtils.date2int(eventDate);
        */
        ContentValues values = new ContentValues();
        values.put(CalendarProvider.COLOR, Event.COLOR_GREEN);
        values.put(CalendarProvider.DESCRIPTION, "Some Description");
        values.put(CalendarProvider.LOCATION, "Some location");
        values.put(CalendarProvider.EVENT, "Eventhhh name");

        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        cal.set(2015, 10, 24, 8, 0);
        int StartDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));
        values.put(CalendarProvider.START, cal.getTimeInMillis());
        values.put(CalendarProvider.START_DAY, StartDayJulian);

        cal.set(2015, 10, 24, 13, 0);
        int endDayJulian = Time.getJulianDay(cal.getTimeInMillis(), TimeUnit.MILLISECONDS.toSeconds(tz.getOffset(cal.getTimeInMillis())));

        values.put(CalendarProvider.END, cal.getTimeInMillis());
        values.put(CalendarProvider.END_DAY, endDayJulian);

        Uri uri = getActivity().getContentResolver().insert(CalendarProvider.CONTENT_URI, values);
    }

    private void getBudleArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            eventTitle = bundle.getString("Title");
            eventDescr = bundle.getString("Desc");
            eventDate = bundle.getString("Date");
        }

    }

    private void initListener() {
        calendar.setOnDayClickListener(new ExtendedCalendarView.OnDayClickListener() {
            @Override
            public void onDayClicked(AdapterView<?> adapter, View view, int position, long id, Day day) {
                getScheduleDetails(day);
                listView.setAdapter(new EventAdapter(getActivity(), R.layout.item_list, itemsList) {
                });
            }
        });
    }

    private void getScheduleDetails(Day day) {

        itemsList = new ArrayList();

        for (Event e : day.getEvents())
        {
            itemsList.add(e);
        }
    }
}
