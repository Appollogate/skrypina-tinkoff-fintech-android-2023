package com.tinkoff.skrypina_tinkoff_fintech_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinkoff.skrypina_tinkoff_fintech_2023.model.FilmData;
import com.tinkoff.skrypina_tinkoff_fintech_2023.net.HttpRequestHandler;
import com.tinkoff.skrypina_tinkoff_fintech_2023.util.FilmJSONParser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
        ImageView filmPoster = findViewById(R.id.filmPoster);

        executor.execute(() -> {
            // Background work - retrieving film data from Kinopoisk server
            String filmDataJSON = new HttpRequestHandler().getFilmDataByIdJSON(filmId);
            FilmData filmData = FilmJSONParser.getFilmData(filmDataJSON);
            String url = filmData.getFilmPosterURL();
            Bitmap image = null;
            try {
                image = BitmapFactory.decodeStream(new URL(url).openStream());
            }
            catch (MalformedURLException e) {
                System.err.println("Couldn't find URL " + url);
            }
            catch (IOException e) {
                System.err.println("Couldn't open stream to download film poster from " + url);
            }
            Bitmap finalImage = image;
            // UI Thread work
            handler.post(() -> {
                filmPoster.setImageBitmap(finalImage);
                filmName.setText(filmData.getFilmName());
                filmDescription.setText(filmData.getFilmDescription());
                filmGenres.setText(String.join(", ", filmData.getFilmGenres()));
                filmCountries.setText(String.join(", ", filmData.getFilmCountries()));
            });
        });



    }
}