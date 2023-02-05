package com.tinkoff.skrypina_tinkoff_fintech_2023.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.MovieContentItem;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    public static List<MovieContentItem> getTopMoviesList(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray films = jsonObject.getAsJsonArray("films");
        List<MovieContentItem> result = new ArrayList<>();
        for (int i = 0; i < films.size(); ++i) {
            JsonObject film = films.get(i).getAsJsonObject();
            String posterImageURL = film.get("posterUrlPreview").getAsString();
            String name = film.get("nameRu").getAsString();
            String year = film.get("year").getAsString();
            String genre = film.getAsJsonArray("genres").
                    get(0).getAsJsonObject().get("genre").getAsString();
            result.add(new MovieContentItem(posterImageURL, name, year, genre));
        }
        return result;
    }
}
