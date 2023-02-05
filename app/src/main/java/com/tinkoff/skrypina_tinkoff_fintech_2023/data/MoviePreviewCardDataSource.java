package com.tinkoff.skrypina_tinkoff_fintech_2023.data;

import com.tinkoff.skrypina_tinkoff_fintech_2023.model.MovieContentItem;
import com.tinkoff.skrypina_tinkoff_fintech_2023.net.HttpRequestHandler;
import com.tinkoff.skrypina_tinkoff_fintech_2023.util.JSONParser;

import java.util.List;

public class MoviePreviewCardDataSource {
    public List<MovieContentItem> loadMoviePreviewCards() {
        String top100FilmsJSON = new HttpRequestHandler().getTop100FilmsJSON();
        return JSONParser.getTopMoviesList(top100FilmsJSON);

//        return List.of(
//                new MoviePreviewCard("", "A", "a", "aa"),
//                new MoviePreviewCard("", "B", "b", "bb"),
//                new MoviePreviewCard("", "C", "c", "cc"),
//                new MoviePreviewCard("", "D", "d", "dd"),
//                new MoviePreviewCard("", "E", "e", "ee"),
//                new MoviePreviewCard("", "F", "f", "ff"),
//                new MoviePreviewCard("", "G", "g", "gg"),
//                new MoviePreviewCard("", "H", "h", "hh"),
//                new MoviePreviewCard("", "I", "i", "ii")
//        );
    }
}
