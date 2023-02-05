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
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.MovieContentItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(MovieContentItem previewCard);
    }

    static class ListItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView genreYearTextView;
        private final ImageView posterView;

        //private final View itemView;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            //this.itemView = itemView;
            nameTextView = itemView.findViewById(R.id.movieName);
            genreYearTextView = itemView.findViewById(R.id.movieGenreAndYear);
            posterView = itemView.findViewById(R.id.moviePreviewPoster);
        }

//        public void bind(final MovieContentItem item, final OnItemClickListener listener) {
//            itemView.setOnClickListener(view -> listener.onItemClick(item));
//        }
    }

    private final List<MovieContentItem> dataset;
    private final OnItemClickListener listener;

    public ListItemAdapter(List<MovieContentItem> dataset, OnItemClickListener listener) {
        this.dataset = dataset;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ListItemViewHolder viewHolder = new ListItemViewHolder(view);

        view.setOnClickListener(v -> {
            final int position = viewHolder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(dataset.get(position));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        MovieContentItem item = dataset.get(position);
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
                handler.post(() -> holder.posterView.setImageBitmap(image));
            } catch (MalformedURLException e) {
                System.err.println("Malformed URL");
            } catch (IOException e) {
                System.err.println("An I/O exception occurred");
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
