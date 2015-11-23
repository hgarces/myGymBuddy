package com.android.ipm.mygymbuddy.adapters;

import android.content.Context;

import com.android.ipm.mygymbuddy.content.Meal;

import java.util.List;

/**
 * Created by joaoelvas on 17/11/15.
 */
public class NutritionAdapter  {

    private Context mContext;
    private List<Meal> mMeals;

    public NutritionAdapter(List<Meal> meals, Context context) {
        this.mContext = context;
        this.mMeals = meals;
    }

}