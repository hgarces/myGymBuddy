package com.android.ipm.mygymbuddy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ipm.mygymbuddy.R;

public class SettingsFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.action_settings);

        return view;
    }
}
