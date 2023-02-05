package com.tinkoff.skrypina_tinkoff_fintech_2023.data;

import com.tinkoff.skrypina_tinkoff_fintech_2023.model.MoviePreviewCard;

import java.util.List;

public class MoviePreviewCardDataSource {
    public List<MoviePreviewCard> loadMoviePreviewCards() {
        // TODO: pull data from Kinopoisk servers
        return List.of(
                new MoviePreviewCard("", "A", "a", "aa"),
                new MoviePreviewCard("", "B", "b", "bb"),
                new MoviePreviewCard("", "C", "c", "cc"),
                new MoviePreviewCard("", "D", "d", "dd"),
                new MoviePreviewCard("", "E", "e", "ee"),
                new MoviePreviewCard("", "F", "f", "ff"),
                new MoviePreviewCard("", "G", "g", "gg"),
                new MoviePreviewCard("", "H", "h", "hh"),
                new MoviePreviewCard("", "I", "i", "ii")
        );
    }
}
