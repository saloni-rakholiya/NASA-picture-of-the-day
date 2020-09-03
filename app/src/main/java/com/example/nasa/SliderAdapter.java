package com.example.nasa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){this.context=context;}


    public int[] slideimages={R.drawable.ic_burst_mode_black_24dp,R.drawable.ic_photo_library_black_24dp,R.drawable.ic_search_black_24dp};
    public String[] slide_heading={"NASA GALLERY","USES APOD","EXPLORE"};
    public String[] slidedesc={"Space and beyond","Library for Nasa Images and videos everyday","SPACE,STARS,GALAXIES"};



    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.sliding,container,false);

        TextView t1=view.findViewById(R.id.text);
        TextView t2=view.findViewById(R.id.textView);
        ImageView img=view.findViewById(R.id.imageView);


        img.setImageResource(slideimages[position]);
        t1.setText(slide_heading[position]);
        t2.setText(slidedesc[position]);



        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
