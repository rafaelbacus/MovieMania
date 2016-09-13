package com.example.rafael.moviemania.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Rafael on 7/6/2016.
 */
public class MovieModel {
    private String Title;
    private String Poster;
    private String Synopsis;
    private String Rating;
    private String ReleaseDate;

    private static final String DEFAULT_DATE_FORMAT = "MMM dd, yyyy";

    public MovieModel(){

    }

    public MovieModel(String title, String poster,
                      String synopsis, String rating,
                      String releaseDate) {
        Title = title;
        Poster = poster;
        Synopsis = synopsis;
        Rating = rating;
        ReleaseDate = releaseDate;
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }

    public String getPoster() {
        return Poster;
    }
    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getSynopsis() {
        return Synopsis;
    }
    public void setSynopsis(String synopsis) {
        Synopsis = synopsis;
    }

    public String getRating() {
        return Rating;
    }
    public void setRating(String rating) {
        Rating = rating;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }
    public void setReleaseDate(String releaseDate) throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-M-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("MMM dd, yyyy");

        Date date = originalFormat.parse(releaseDate);

        ReleaseDate = targetFormat.format(date);
    }
}
