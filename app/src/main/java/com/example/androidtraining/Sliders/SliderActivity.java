package com.example.androidtraining.Sliders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.androidtraining.R;

public class SliderActivity extends AppCompatActivity {
    ViewPager viewPager;

    Adapter adapter;

    int images[] = {R.drawable.movie1, R.drawable.movie2, R.drawable.movie3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        viewPager = (ViewPager) findViewById(R.id.viewpagers);
        adapter = new Adapter(SliderActivity.this, images);
        viewPager.setAdapter(adapter);

    }
}