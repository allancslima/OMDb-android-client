package com.allancslima.omdbclient.data.network.model;

import com.allancslima.omdbclient.data.db.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public class MovieResponse {

    @Expose
    @SerializedName("Title")
    private String title;

    @Expose
    @SerializedName("Year")
    // String type: API can returns "2006-2007" for example
    private String year;

    @Expose
    @SerializedName("Genre")
    private String genre;

    @Expose
    @SerializedName("Actors")
    private String actors;

    @Expose
    @SerializedName("imdbRating")
    private String imdbRating;

    @Expose
    @SerializedName("imdbVotes")
    private String imdbVotes;

    @Expose
    @SerializedName("Production")
    private String production;

    @Expose
    @SerializedName("Response")
    private boolean response;

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

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public Movie getMovie() {
        Movie movie = new Movie();
        movie.setTitle(getTitle());
        movie.setYear(getYear());
        movie.setGenre(getGenre());
        movie.setActors(getActors());
        movie.setImdbRating(getImdbRating());
        movie.setImdbVotes(getImdbVotes());
        movie.setProduction(getProduction());

        return movie;
    }
}