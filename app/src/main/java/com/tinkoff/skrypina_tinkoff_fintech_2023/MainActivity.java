package com.tinkoff.skrypina_tinkoff_fintech_2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.tinkoff.skrypina_tinkoff_fintech_2023.adapter.ListItemAdapter;
import com.tinkoff.skrypina_tinkoff_fintech_2023.data.MoviePreviewCardDataSource;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.MoviePreviewCard;

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
            // Background work - retrieving the data from Kinopoisk servers
            List<MoviePreviewCard> dataset = new MoviePreviewCardDataSource().loadMoviePreviewCards();
            // UI Thread work
            handler.post(() -> {
                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                recyclerView.setAdapter(new ListItemAdapter(dataset));
                recyclerView.setHasFixedSize(true);
            });
        });


    }
}