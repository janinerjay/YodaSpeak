package com.example.christiandegenova.yodaspeaks;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class YodaFragment extends Fragment {

    private interface YodaListener {};

    private final String LOG_TAG = MessageActivity.class.getSimpleName();
    private YodaListener listener;
    private TextView textView;
    private String yodaText;

    View view;
    public YodaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_yoda, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        view = getView();
        if (view != null) {
            Log.i(LOG_TAG, "the fragment has started");
            textView = (TextView) view.findViewById(R.id.yodaText2);
            Log.i(LOG_TAG, "string s = " + yodaText);
            textView.setText(yodaText);
            if (textView == null) {
                Log.i(LOG_TAG, "text view is equal to null");
            }
        }
    }

    public void setText (String s) {
        Log.i(LOG_TAG, "yodaText = " + yodaText);
        yodaText = s;
    }
}