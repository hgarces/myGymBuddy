package com.android.ipm.mygymbuddy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.android.ipm.mygymbuddy.R;
import com.android.ipm.mygymbuddy.content.Meal;

import java.util.List;

public class NutritionFragment extends Fragment implements View.OnClickListener{

    Button firstMealBtn, secondMealBtn, thirdMealBtn, fourthMealBts, fifthMealBtn, sixthMealBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_nutrition, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.nutrition);
        firstMealBtn = (Button) view.findViewById(R.id.nutrition_first_meal);
        secondMealBtn = (Button) view.findViewById(R.id.nutrition_second_meal);
        thirdMealBtn = (Button) view.findViewById(R.id.nutrition_third_meal);
        fourthMealBts = (Button) view.findViewById(R.id.nutrition_fourth_meal);
        fifthMealBtn = (Button) view.findViewById(R.id.nutrition_fifth_meal);
        sixthMealBtn = (Button) view.findViewById(R.id.nutrition_sixth_meal);

        firstMealBtn.setOnClickListener(this);

        secondMealBtn.setOnClickListener(this);

        thirdMealBtn.setOnClickListener(this);

        fourthMealBts.setOnClickListener(this);

        fifthMealBtn.setOnClickListener(this);

        sixthMealBtn.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        switch (v.getId()) {
            case R.id.nutrition_first_meal:
                fragment = new BreakfastFragment();
                break;
            case R.id.nutrition_second_meal:
                fragment = new MorningMealFragment();
                break;
            case R.id.nutrition_third_meal:
                fragment = new LunchFragment();
                break;
            case R.id.nutrition_fourth_meal:
                fragment = new AfternoonMealFragment();
                break;
            case R.id.nutrition_fifth_meal:
                fragment = new DinnerFragment();
                break;
            case R.id.nutrition_sixth_meal:
                fragment = new NightMealFragment();
        }
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).addToBackStack("nutri").commit();

    }
}
