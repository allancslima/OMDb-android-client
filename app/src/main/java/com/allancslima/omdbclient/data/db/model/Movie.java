package com.allancslima.omdbclient.data.db.model;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public class Movie {

    private String title;
    private String year;
    private String genre;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}