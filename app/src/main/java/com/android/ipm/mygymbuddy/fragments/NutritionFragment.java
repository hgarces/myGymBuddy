package com.android.ipm.mygymbuddy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.ipm.mygymbuddy.R;
import com.android.ipm.mygymbuddy.content.Meal;

import java.util.List;

public class NutritionFragment extends Fragment {
    private List<Meal> meals;
    private ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_nutrition, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.nutrition);

        return view;
    }

    private void createMeals() {
        //TODO ADD DRAWABLES TO CREATE MEALS
        //meals.add(new Meal(R.string.breakfast, null));

    }
}
