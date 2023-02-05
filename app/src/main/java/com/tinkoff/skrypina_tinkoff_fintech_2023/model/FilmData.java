package com.tinkoff.skrypina_tinkoff_fintech_2023.model;

import java.util.List;

public class FilmData {
    private final String filmPosterURL;
    private final String filmName;
    private final String filmDescription;
    private final List<String> filmGenres;
    private final List<String> filmCountries;

    public FilmData(String filmPosterURL, String filmName, String filmDescription, List<String> filmGenres, List<String> filmCountries) {
        this.filmPosterURL = filmPosterURL;
        this.filmName = filmName;
        this.filmDescription = filmDescription;
        this.filmGenres = filmGenres;
        this.filmCountries = filmCountries;
    }

    public String getFilmPosterURL() {
        return filmPosterURL;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public List<String> getFilmGenres() {
        return filmGenres;
    }

    public List<String> getFilmCountries() {
        return filmCountries;
    }
}
