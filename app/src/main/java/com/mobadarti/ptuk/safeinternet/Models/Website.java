package com.mobadarti.ptuk.safeinternet.Models;

public class Website {
    String Name;
    String Url;

    public Website(String name, String url) {
        Name = name;
        Url = url;
    }

    public String getName() {
        return Name;
    }

    public String getUrl() {
        return Url;
    }

    @Override
    public String toString() {
        return Name;
    }
}
