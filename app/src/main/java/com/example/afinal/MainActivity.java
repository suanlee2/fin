package com.example.afinal;


import com.example.lib.MyClass;

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


import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static RequestQueue requestQueue;
    String a = "";
    String API_KEY = "54d59df2-06df-4fef-b91e-ebc272641061";
    private static final String TAG = "fin:Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);

        final Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText year = (EditText)findViewById(R.id.year);
                final EditText date = (EditText)findViewById(R.id.date);
                final EditText month = (EditText)findViewById(R.id.month);
                final TextView holiday = (TextView)findViewById(R.id.holiday);

                Log.d(TAG, "check");
                a = Uri.parse("https://holidayapi.com/v1/holidays?")
                        .buildUpon()
                        .appendQueryParameter("key", API_KEY)
                        //.appendQueryParameter("country", country.getT)
                        .appendQueryParameter("year", year.getText().toString())
                        .appendQueryParameter("month", month.getText().toString())
                        .appendQueryParameter("date", date.getText().toString())
                        .build()
                        .toString();
                startAPICall();
                holiday.setText(a);
            }
        });
    }
    protected
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://holidayapi.com/v1/holidays",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone(response);
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
