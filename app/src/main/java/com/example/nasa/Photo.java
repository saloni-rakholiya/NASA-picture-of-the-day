package com.example.nasa;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Photo implements Serializable {

    private String date;
    private String explanation;
    private String media_type;
    private String url;
    private String title;

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}