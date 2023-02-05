package com.tinkoff.skrypina_tinkoff_fintech_2023.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tinkoff.skrypina_tinkoff_fintech_2023.R;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.MoviePreviewCard;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {

    public ListItemAdapter(List<MoviePreviewCard> dataset) {
        this.dataset = dataset;
    }

    static class ListItemViewHolder extends RecyclerView.ViewHolder {

        public ListItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.movieName);
            genreYearTextView = itemView.findViewById(R.id.movieGenreAndYear);
            posterView = itemView.findViewById(R.id.moviePreviewPoster);
        }

        private final TextView nameTextView;
        private final TextView genreYearTextView;

        private final ImageView posterView;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View adapterLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListItemViewHolder(adapterLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        MoviePreviewCard item = dataset.get(position);
        holder.nameTextView.setText(item.getMovieName());
        holder.genreYearTextView.setText(String.format("%s (%s)", item.getGenre(), item.getYear()));
        loadPicture(holder, item.getPosterImageURL());
    }

    private void loadPicture(ListItemViewHolder holder, String url) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                Bitmap image = BitmapFactory.decodeStream(new URL(url).openStream());
                handler.post(() -> {
                   holder.posterView.setImageBitmap(image);
                });
            } catch (MalformedURLException e) {
                System.err.println("Malformed URL");
            }
            catch (IOException e) {
                System.err.println("An I/O exception occurred");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    private final List<MoviePreviewCard> dataset;
}
