package com.tinkoff.skrypina_tinkoff_fintech_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.tinkoff.skrypina_tinkoff_fintech_2023.model.FilmData;
import com.tinkoff.skrypina_tinkoff_fintech_2023.net.HttpRequestHandler;
import com.tinkoff.skrypina_tinkoff_fintech_2023.util.FilmJSONParser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FilmActivity extends AppCompatActivity {

    private String filmId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        filmId = getIntent().getStringExtra("FILM_ID");
        setContents();
    }

    private void setContents() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        TextView filmName = findViewById(R.id.filmName);
        TextView filmDescription = findViewById(R.id.filmDescription);
        TextView filmGenres = findViewById(R.id.genres);
        TextView filmCountries = findViewById(R.id.countries);

        executor.execute(() -> {
            // Background work - retrieving film data from Kinopoisk server
            String filmDataJSON = new HttpRequestHandler().getFilmDataByIdJSON(filmId);
            FilmData filmData = FilmJSONParser.getFilmData(filmDataJSON);
            // UI Thread work
            handler.post(() -> {
                filmName.setText(filmData.getFilmName());
                filmDescription.setText(filmData.getFilmDescription());
                filmGenres.setText(String.join(", ", filmData.getFilmGenres()));
                filmCountries.setText(String.join(", ", filmData.getFilmCountries()));
            });
        });



    }
}