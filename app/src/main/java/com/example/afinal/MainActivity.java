package com.example.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        findViewById(R.id.search).setOnClickListener(v -> {
            holiday.setText("d");
        });
    }
}
