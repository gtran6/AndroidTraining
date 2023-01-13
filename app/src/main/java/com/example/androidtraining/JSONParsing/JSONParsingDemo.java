package com.example.androidtraining.JSONParsing;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtraining.R;

public class JSONParsingDemo extends AppCompatActivity {
    JSONParser jsonParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonparsing_demo);

        try {
            jsonParser = (JSONParser) new JSONParser(this, this).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}