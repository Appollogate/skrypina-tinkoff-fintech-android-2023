package com.tinkoff.skrypina_tinkoff_fintech_2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tinkoff.skrypina_tinkoff_fintech_2023.adapter.ListItemAdapter;
import com.tinkoff.skrypina_tinkoff_fintech_2023.data.MoviePreviewCardDataSource;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.MoviePreviewCard;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<MoviePreviewCard> dataset = new MoviePreviewCardDataSource().loadMoviePreviewCards();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new ListItemAdapter(dataset));
        recyclerView.setHasFixedSize(true);
    }
}