package com.example.christiandegenova.yodaspeaks;

/**
 * Authors: Christian DeGenova and Janine Jay
 * March 7th, 2016
 * Yoda Application
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessageActivity extends FragmentActivity {

    private ShareActionProvider shareActionProvider;
    String text = "YodaSpeak is the best app! Just type in a sentence and it will provide you with " +
            "how Yoda would say it. Try it out! Disappointed, You Won't be!";
    private final String LOG_TAG = MessageActivity.class.getSimpleName();
    final Yoda yodaObj = new Yoda();
    private String mess = "";
    private String send;
    //private Intent intent;
    String yodaSpeakStr = null;
    private YodaFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        //YodaFragment frag = (YodaFragment) getFragmentManager().findFragmentById(R.id.yoda_frag);
        //intent = new Intent(this, YodaActivity.class);
    }

    public void onClick (View view) {
        EditText edt = (EditText) findViewById(R.id.editText);
        mess = edt.getText().toString();

        getMessage(mess);
        new FetchYodaFact().execute(mess);
    }

    public void getMessage (String string) {
        mess = string;
        String[] splited = mess.split("\\s+");

        send = makeString(splited);
    }

    public String makeString(String[] split) {
        StringBuilder builder = new StringBuilder();
        for(String s : split) {
            builder.append(s);
            builder.append("+");
        }
        return builder.toString();
    }

    private class FetchYodaFact extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground (String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String yodaSpeak = "";
            String yodaSpeakStr = null;

            try {
                // These two need to be declare outside the try/catch
                // so that they can be closed in the finally block.

                //1- Connect to the internet using a URL connection object
                URL url = new URL("https://yoda.p.mashape.com/yoda?mashape-key=9fthKFV2SDmsh2S83uPfjsojvodvp1vask8jsnC7Ht3HcrJoGO&sentence=" + send);
                Log.i(LOG_TAG, "url= " + url.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //2- read from input buffer
                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                //3- call some code to parse the String
                yodaSpeakStr = getBufferStringFromBuffer(reader);
                yodaSpeak = Yoda.getYodaSpeak(yodaSpeakStr);

                //finally return the yodaSpeak!
            }catch (IOException e){
                Log.e(LOG_TAG, "error= " + e.getMessage());
            }finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    }catch (IOException e) {
                        Log.e(LOG_TAG, "error= " + e.getMessage());
                    }
                }
            }
            return yodaSpeakStr;
        }

        protected void onPostExecute (String result) {
            if (result != null) {
                //intent.putExtra(Yoda.YODA_SPEAK, result);
                //startActivity(intent);
                fragment = new YodaFragment();
                fragment.setText(result);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
                Log.i(LOG_TAG, "set the text to: " + result);
            }
        }

        private String getBufferStringFromBuffer(BufferedReader br) {
            String line;
            StringBuffer buffer = new StringBuffer();
            try {
                while ((line = br.readLine()) != null) {
                    buffer.append(line + "\n");
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "error= " + e.getMessage());
                return null;
            }
            return buffer.toString();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        setIntent(text);
        return super.onCreateOptionsMenu(menu);
    }

    private void setIntent(String Intent){
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.ideas:
                //code to run the create order item when clicked
                Intent intent = new Intent(this,IdeasActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                View root = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
                root.setBackgroundColor(Color.MAGENTA);
                return true;
            case R.id.action_share:
                //code to run action share
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}