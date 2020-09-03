package com.example.nasa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class abt extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abt);
        t=findViewById(R.id.textView3);
    t.setText(getIntent().getStringExtra("info").toString());

    }
}
