package com.tinkoff.skrypina_tinkoff_fintech_2023.model;

public class MovieContentItem {
    public MovieContentItem(String posterImageURL, String movieName, String year, String genre) {
        this.posterImageURL = posterImageURL;
        this.movieName = movieName;
        this.year = year;
        this.genre = genre;
    }

    public String getPosterImageURL() {
        return posterImageURL;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    private final String posterImageURL;
    private final String movieName;
    private final String year;
    private final String genre;
}
