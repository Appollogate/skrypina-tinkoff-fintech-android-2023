package com.tinkoff.skrypina_tinkoff_fintech_2023.data;

import com.tinkoff.skrypina_tinkoff_fintech_2023.model.FilmContentItem;
import com.tinkoff.skrypina_tinkoff_fintech_2023.net.HttpRequestHandler;
import com.tinkoff.skrypina_tinkoff_fintech_2023.util.JSONParser;
import java.util.List;

public class FilmPreviewCardDataSource {
    public List<FilmContentItem> loadMoviePreviewCards() {
        String top100FilmsJSON = new HttpRequestHandler().getTop100FilmsJSON();
        return JSONParser.getTopMoviesList(top100FilmsJSON);
    }
}
