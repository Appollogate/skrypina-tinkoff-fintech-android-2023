package com.tinkoff.skrypina_tinkoff_fintech_2023.model;

public class FilmContentItem {
    public FilmContentItem(String filmId, String posterImageURLPreview, String movieName, String year, String genre) {
        this.posterImageURLPreview = posterImageURLPreview;
        this.movieName = movieName;
        this.year = year;
        this.genre = genre;
        this.filmId = filmId;
    }

    public String getPosterImageURLPreview() {
        return posterImageURLPreview;
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

    public String getFilmId() {
        return filmId;
    }

    private final String filmId;
    private final String posterImageURLPreview;
    private final String movieName;
    private final String year;
    private final String genre;
}
