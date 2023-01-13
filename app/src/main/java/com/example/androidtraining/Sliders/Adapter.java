package com.example.androidtraining.Sliders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.androidtraining.R;

import java.util.Objects;

public class Adapter extends PagerAdapter {
    //create object
    Context context;

    //array of images
    int images[];

    ImageView imageView;

    //layout inflater
    LayoutInflater layoutInflater;

    //viewpager constructor
    public Adapter(Context context, int[] images) {
        this.context = context;
        this.images = images;

        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //inflating the view imageforviewpager.xml
        View itemview = layoutInflater.inflate(R.layout.imageforviewpager, container, false);
        imageView = (ImageView) itemview.findViewById(R.id.sliders);
        imageView.setImageResource(images[position]);
        //adding the view
        Objects.requireNonNull(container).addView(itemview);

        return itemview;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //((ViewPager)container).removeView(new LinearLayout(object));
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
