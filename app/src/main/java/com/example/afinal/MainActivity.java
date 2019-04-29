package com.example.afinal;


import com.example.lib.MyClass;

import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;


import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    private static RequestQueue requestQueue;
    private TextView result;
    private EditText year;
    private EditText date;
    private EditText month;
    private Button search;
    String a = "";
    String API_KEY = "54d59df2-06df-4fef-b91e-ebc272641061";
    private static final String TAG = "fin:Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);
        result = (TextView)findViewById(R.id.holiday);
        year = (EditText)findViewById(R.id.year);
        date = (EditText)findViewById(R.id.date);
        month = (EditText)findViewById(R.id.month);
        search = findViewById(R.id.search);
        //find();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d(TAG, "check");
                a = Uri.parse("https://holidayapi.com/v1/holidays?key=b7bcc8be-0de4-4d4d-b540-b859755cf263&country=US")
                        .buildUpon()
                        .appendQueryParameter("key", API_KEY)
                        //.appendQueryParameter("country", country.getT)
                        .appendQueryParameter("year", year.getText().toString())
                        .appendQueryParameter("month", month.getText().toString())
                        .appendQueryParameter("date", date.getText().toString())
                        .build()
                        .toString();
                find();
            }
        });
    }
    public void find() {
        String url = "https://holidayapi.com/v1/holidays?key=b7bcc8be-0de4-4d4d-b540-b859755cf263&country=US";
        JsonObjectRequest j = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String tmp = response.getString("name");
                    JSONArray array = response.getJSONArray("holidays");
                    if (array.length() != 0) {
                        JSONObject name = array.getJSONObject(0);
                        String country = name.getString("name");
                        result.setText(country);
                    } else {
                        result.setText("Not a holiday");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue que = Volley.newRequestQueue(this);
        que.add(j);
    }
    /*void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://holidayapi.com/v1/holidays",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            set(response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    protected void set(String input) {
        //TextView result = findViewById(R.id.holiday);
        result.setText(MyClass.holiday(input));
        result.setTextColor(Color.BLUE);
    }
    /**
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     */
    void apiCallDone(final JSONObject response) {
        MyClass.holiday("");
        try {

            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            Log.i(TAG, response.get("name").toString());
        } catch (JSONException ignored) { }
    }
}
