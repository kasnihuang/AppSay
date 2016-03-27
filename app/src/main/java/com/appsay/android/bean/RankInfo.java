package com.appsay.android.bean;

import android.graphics.Bitmap;

/**
 * Created by kasni.huang on 3/27/16.
 */
public class RankInfo {
    private String label;
    private Bitmap iconOne;
    private Bitmap iconTwo;
    private Bitmap iconThree;

    public RankInfo(String label, Bitmap iconOne, Bitmap iconTwo, Bitmap iconThree) {
        this.label = label;
        this.iconOne = iconOne;
        this.iconTwo = iconTwo;
        this.iconThree = iconThree;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Bitmap getIconOne() {
        return iconOne;
    }

    public void setIconOne(Bitmap iconOne) {
        this.iconOne = iconOne;
    }

    public Bitmap getIconTwo() {
        return iconTwo;
    }

    public void setIconTwo(Bitmap iconTwo) {
        this.iconTwo = iconTwo;
    }

    public Bitmap getIconThree() {
        return iconThree;
    }

    public void setIconThree(Bitmap iconThree) {
        this.iconThree = iconThree;
    }
}
