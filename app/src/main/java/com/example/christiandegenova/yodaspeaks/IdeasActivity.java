package com.example.christiandegenova.yodaspeaks;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class IdeasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ListView listView = (ListView)findViewById(R.id.ideas);
        String [] ideas1 = getResources().getStringArray(R.array.ideas2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, ideas1);
        listView.setAdapter(adapter);


    }
}
