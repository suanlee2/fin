package com.example.afinal;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText year;
    EditText month;
    EditText date;
    TextView holiday;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        year = (EditText)findViewById(R.id.year);
        date = (EditText)findViewById(R.id.date);
        month = (EditText)findViewById(R.id.month);
        holiday = (TextView)findViewById(R.id.holiday);
        search = (Button)findViewById(R.id.search);
        Button btn = (Button) findViewById(R.id.search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holiday.setText("Yay!!");
            }
        });
    }
}
