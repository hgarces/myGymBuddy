package com.android.ipm.mygymbuddy.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.ipm.mygymbuddy.R;
import com.android.ipm.mygymbuddy.adapters.EventAdapter;
import com.tyczj.extendedcalendarview.Day;
import com.tyczj.extendedcalendarview.Event;
import com.tyczj.extendedcalendarview.ExtendedCalendarView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ExtendedCalendarView calendar;
    RecyclerView recyclerView;
    ArrayList itemsList;
    FloatingActionButton newActivityButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        calendar = (ExtendedCalendarView) rootView.findViewById(R.id.calendar);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.events_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        newActivityButton = (FloatingActionButton) rootView.findViewById(R.id.fab_button);

        initEventListeners();
        return rootView;
    }

    private void initEventListeners() {

        calendar.setOnDayClickListener(new ExtendedCalendarView.OnDayClickListener() {
            int i = 0;

            @Override
            public void onDayClicked(AdapterView<?> adapter, View view, int position, long id, Day day) {

                getScheduleDetails(day);
                recyclerView.setAdapter(new EventAdapter(itemsList));
                i++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        i = 0;
                    }
                };
                if(i == 1) {
                    handler.postDelayed(r, 350);
                }
                else if(i == 2) {
                    Fragment fragment = new NewActivityFragment();
                    getActivity().getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment, null).addToBackStack(null)
                            .commit();
                }
            }
        });

        newActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new NewActivityFragment();
                getActivity().getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment, null).addToBackStack(null)
                        .commit();
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
