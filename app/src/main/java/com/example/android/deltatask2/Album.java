package com.example.android.deltatask2;

import android.graphics.drawable.Drawable;

/**
 * Created by Admin on 23-06-2017.
 */

public class Album {
    private String mCaption;
    private Drawable mImage;

    public Album(String caption, Drawable image) {
        this.mCaption = "\"" + caption + "\"";
        this.mImage = image;
    }

    public String getCaption() {
        return this.mCaption;
    }

    public Drawable getImage() {
        return this.mImage;
    }

}
