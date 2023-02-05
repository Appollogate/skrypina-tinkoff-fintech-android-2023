package com.tinkoff.skrypina_tinkoff_fintech_2023.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.FilmContentItem;

import com.google.gson.JsonObject;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.FilmData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilmJSONParser {
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

    public static FilmData getFilmData(String json) {
        JsonObject film = JsonParser.parseString(json).getAsJsonObject();
        String filmPosterURL = film.get("posterUrl").getAsString();
        String filmName = film.get("nameRu").getAsString();
        String filmDescription = film.get("description").getAsString();
        List<String> genres = film
                .getAsJsonArray("genres")
                .asList()
                .stream()
                .map(jsonEl -> jsonEl.getAsJsonObject().get("genre").getAsString())
                .collect(Collectors.toList());
        List<String> countries = film
                .getAsJsonArray("countries")
                .asList()
                .stream()
                .map(jsonEl -> jsonEl.getAsJsonObject().get("country").getAsString())
                .collect(Collectors.toList());
        return new FilmData(filmPosterURL, filmName, filmDescription,
                Collections.unmodifiableList(genres),
                Collections.unmodifiableList(countries));
    }
}
