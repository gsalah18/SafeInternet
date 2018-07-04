package com.mobadarti.ptuk.safeinternet.Models;


import android.net.Uri;

public class Video {
    private String Title;
    private String Uri;

    public Video(String title, String url) {
        Title = title;
        Uri = url;
    }

    public String getTitle() {
        return Title;
    }

    public String getUrl() {
        return Uri;
    }

    @Override
    public String toString() {
        return Title;
    }
}
