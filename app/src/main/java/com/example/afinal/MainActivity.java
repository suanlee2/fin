package com.example.afinal;


import com.example.lib.MyClass;

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
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    private static RequestQueue requestQueue;
    private TextView result;
    private EditText country;
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
        country = (EditText) findViewById(R.id.country);
        date = (EditText)findViewById(R.id.date);
        month = (EditText)findViewById(R.id.month);
        search = findViewById(R.id.search);
        //find();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find();
            }
        });
    }
    public void find() {
        String one = "https://holidayapi.com/v1/holidays?key=54d59df2-06df-4fef-b91e-ebc272641061&country=";
        String two = country.getText().toString();
        String three = "&year=2018";
        String url = one + two + three;
        //String y = "https://holidayapi.com/v1/holidays?key=54d59df2-06df-4fef-b91e-ebc272641061&country=US&year=2018";
        JsonObjectRequest j = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String yy = "2018";
                    String mm = month.getText().toString();
                    String dd = date.getText().toString();
                    String input = yy + "-" + mm + "-" + dd;
                    JSONArray array = response.getJSONArray("holidays");
                    if (array.length() != 0) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject name = array.getJSONObject(i);
                            if (name.getString("date").equals(input)) {
                                result.setText(name.getString("name"));
                            }
                        }
                    }
                    //result.setText("Not a holiday");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        RequestQueue que = Volley.newRequestQueue(this);
        que.add(j);
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
