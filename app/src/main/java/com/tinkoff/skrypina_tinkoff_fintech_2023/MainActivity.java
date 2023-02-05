package com.tinkoff.skrypina_tinkoff_fintech_2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.tinkoff.skrypina_tinkoff_fintech_2023.adapter.ListItemAdapter;
import com.tinkoff.skrypina_tinkoff_fintech_2023.data.FilmPreviewCardDataSource;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.FilmContentItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Background work - retrieving top-100 film data from Kinopoisk servers
            List<FilmContentItem> dataset = new FilmPreviewCardDataSource().loadMoviePreviewCards();
            // UI Thread work
            handler.post(() -> {
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                // Set list item data + onItemClick listener
                recyclerView.setAdapter(new ListItemAdapter(dataset, previewCard -> {
                    //Toast.makeText(this, previewCard.getFilmId(), Toast.LENGTH_SHORT).show();
                    // TODO: get movie id and switch activity
                    Intent intent = new Intent(getBaseContext(), FilmActivity.class);
                    intent.putExtra("FILM_ID", previewCard.getFilmId());
                    startActivity(intent);
                }));
                recyclerView.setHasFixedSize(true);
            });
        });


    }
}