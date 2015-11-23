package com.android.ipm.mygymbuddy.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.ipm.mygymbuddy.ExercisesActivity;
import com.android.ipm.mygymbuddy.HidratationActivity;
import com.android.ipm.mygymbuddy.R;
import com.android.ipm.mygymbuddy.RunningActivity;

public class NewActivityFragment extends Fragment implements View.OnClickListener{

    ImageButton activity1;
    ImageButton activity2;
    ImageButton activity3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_activity, container, false);

        activity1 = (ImageButton) v.findViewById(R.id.activity1_button);
        activity2 = (ImageButton) v.findViewById(R.id.activity2_button);
        activity3 = (ImageButton) v.findViewById(R.id.activity3_button);

        activity1.setOnClickListener(this);
        activity2.setOnClickListener(this);
        activity3.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.activity1_button:
                intent = new Intent(getActivity(), HidratationActivity.class);
                startActivity(intent);
                break;
            case R.id.activity2_button:
                intent = new Intent(getActivity(), ExercisesActivity.class);
                startActivity(intent);
                break;
            case R.id.activity3_button:
                intent = new Intent(getActivity(), RunningActivity.class);
                startActivity(intent);
                break;
        }
    }
}
