package com.example.christiandegenova.yodaspeaks;

/**
 * Authors: Christian DeGenova and Janine Jay
 * March 7th, 2016
 * Yoda Application
 */

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class YodaActivity extends Activity {

    private final String LOG_TAG = MessageActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoda);

        if (getIntent().getExtras() != null) {
            String yodaSpeak = (String) getIntent().getExtras().get(Yoda.YODA_SPEAK);
            TextView textView = (TextView) findViewById(R.id.yodaText);
            textView.setText(yodaSpeak.toString());
        }
    }
    public void onClick1 (View view) {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }
}