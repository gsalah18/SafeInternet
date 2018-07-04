package com.mobadarti.ptuk.safeinternet.Models;

public class Book {
    private String title;
    private String link;

    public Book(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
