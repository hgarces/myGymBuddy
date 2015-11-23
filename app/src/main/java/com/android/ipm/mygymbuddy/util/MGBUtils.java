package com.android.ipm.mygymbuddy.util;

public class MGBUtils {

    public static int[] date2int(String date) {
        String[] items = date.split("/");
        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {};
        }

        return results;
    }
}
