package com.android.ipm.mygymbuddy.content;

import android.graphics.drawable.Drawable;

/**
 * Created by joaoelvas on 17/11/15.
 */
public class Meal {

    private Drawable mealIcon;
    private String nameOfTheMeal;

    public Meal(String name, Drawable icon) {
        this.mealIcon = icon;
        this.nameOfTheMeal = name;
    }

    public Drawable getMealIcon() {
        return mealIcon;
    }

    public String getNameOfTheMeal() {
        return nameOfTheMeal;
    }
}