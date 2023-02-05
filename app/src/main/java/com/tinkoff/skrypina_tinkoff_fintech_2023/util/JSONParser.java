package com.tinkoff.skrypina_tinkoff_fintech_2023.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.FilmContentItem;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    public static List<FilmContentItem> getTopMoviesList(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray films = jsonObject.getAsJsonArray("films");
        List<FilmContentItem> result = new ArrayList<>();
        for (int i = 0; i < films.size(); ++i) {
            JsonObject film = films.get(i).getAsJsonObject();
            String filmId = film.get("filmId").getAsString();
            String posterImageURL = film.get("posterUrlPreview").getAsString();
            String name = film.get("nameRu").getAsString();
            String year = film.get("year").getAsString();
            String genre = film.getAsJsonArray("genres").
                    get(0).getAsJsonObject().get("genre").getAsString();
            result.add(new FilmContentItem(filmId, posterImageURL, name, year, genre));
        }
        return result;
    }
}
