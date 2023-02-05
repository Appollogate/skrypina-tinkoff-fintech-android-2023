package com.tinkoff.skrypina_tinkoff_fintech_2023;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tinkoff.skrypina_tinkoff_fintech_2023.adapter.ListItemAdapter;
import com.tinkoff.skrypina_tinkoff_fintech_2023.data.FilmPreviewCardDataSource;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.FilmContentItem;
import com.tinkoff.skrypina_tinkoff_fintech_2023.net.NetworkUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView errorText;
    private Button errorButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        errorText = findViewById(R.id.networkErrorTextViewMain);
        errorButton = findViewById(R.id.retryButtonMain);

        errorButton.setOnClickListener(view -> {
            disableNetworkWarning();
            fillContent();
        });

        fillContent();
    }

    void fillContent() {
        // First, check internet connection status
        if (!NetworkUtil.isNetworkAvailable(this)) {
            enableNetworkWarning();
            return;
        }
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
                    Intent intent = new Intent(getBaseContext(), FilmActivity.class);
                    intent.putExtra("FILM_ID", previewCard.getFilmId());
                    startActivity(intent);
                }));
                recyclerView.setHasFixedSize(true);
            });
        });
    }

    void enableNetworkWarning() {
        recyclerView.setVisibility(View.GONE);
        errorText.setVisibility(View.VISIBLE);
        errorButton.setVisibility(View.VISIBLE);
    }

    void disableNetworkWarning() {
        errorButton.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}