package com.example.christiandegenova.yodaspeaks;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Authors: Christian DeGenova and Janine Jay
 * March 7th, 2016
 * Yoda Application
 */

import android.util.Log;

public class Yoda {

    public static final String YODA_SPEAK = "YODA_SPEAK";
    private static final String LOG_TAG = MessageActivity.class.getSimpleName();

    public Yoda () {}

    public static String getYodaSpeak(String yodaSpeakJsonStr){
        String yodaSpeak = null;
        yodaSpeak = yodaSpeakJsonStr;
        Log.i(LOG_TAG, yodaSpeak);
        return yodaSpeak;
    }
}
