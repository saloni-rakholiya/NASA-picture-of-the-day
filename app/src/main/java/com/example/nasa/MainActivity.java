package com.example.nasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SliderAdapter sliderAdapter;
    ViewPager viewPager;
    private TextView[] dots;
    LinearLayout linearLayout;
    Button prev,next;
    int currentpg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewp);
        linearLayout=findViewById(R.id.lin);
        prev=findViewById(R.id.button);
        next=findViewById(R.id.button2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(next.getText().equals("Finish"))
                {
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                }
                else
                viewPager.setCurrentItem(currentpg+1);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewPager.setCurrentItem(currentpg-1);


            }
        });



        sliderAdapter=new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);


        adddotindicator(0);
     viewPager.addOnPageChangeListener(viewlistener);
    }

    public void adddotindicator(int position){
        dots=new TextView[3];
        linearLayout.removeAllViews();

        for(int i=0;i<3;++i)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.WHITE);
            linearLayout.addView(dots[i]);

        }

        if(dots.length>0)
        {
            dots[position].setTextColor(Color.RED);

        }

    }

    ViewPager.OnPageChangeListener viewlistener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            adddotindicator(position);
            currentpg=position;

            if(position==0)
            {
                next.setEnabled(true);
                prev.setEnabled(false);
                prev.setVisibility(View.INVISIBLE);
                next.setText("Next");
                prev.setText("");
            }
            else if(position==dots.length-1)
        {
            next.setEnabled(true);
            prev.setEnabled(true);
            prev.setVisibility(View.VISIBLE);
            next.setText("Finish");
            prev.setText("Prev");

        }
            else {
                next.setEnabled(true);
                prev.setEnabled(true);
                 prev.setVisibility(View.VISIBLE);
                next.setText("Next");
                prev.setText("Prev");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
