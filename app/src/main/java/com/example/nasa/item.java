package com.example.nasa;

public class item {
    String name;
    int imageresource;

    public item(String name, int imageresource) {
        this.name = name;
        this.imageresource = imageresource;
    }


    public String getName() {
        return name;
    }

    public int getImageresource() {
        return imageresource;
    }
}
