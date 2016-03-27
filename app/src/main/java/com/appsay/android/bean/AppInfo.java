package com.appsay.android.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by kasni.huang on 3/22/16.
 */
public class AppInfo {
    private Drawable appIcon;
    private String name;
    private String version;
    private String size;
    private String rate;
    private String rateDes;

    public AppInfo(Drawable appIcon, String name, String version, String size, String rate, String rateDes) {
        this.appIcon = appIcon;
        this.name = name;
        this.version = version;
        this.size = size;
        this.rate = rate;
        this.rateDes = rateDes;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateDes() {
        return rateDes;
    }

    public void setRateDes(String rateDes) {
        this.rateDes = rateDes;
    }
}
