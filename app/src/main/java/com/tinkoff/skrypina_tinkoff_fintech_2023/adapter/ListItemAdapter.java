package com.tinkoff.skrypina_tinkoff_fintech_2023.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tinkoff.skrypina_tinkoff_fintech_2023.R;
import com.tinkoff.skrypina_tinkoff_fintech_2023.model.MoviePreviewCard;
import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder> {

    public ListItemAdapter(List<MoviePreviewCard> dataset) {
        this.dataset = dataset;
    }

    static class ListItemViewHolder extends RecyclerView.ViewHolder {

        public ListItemViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.movieName);
            genreYearTextView = itemView.findViewById(R.id.movieGenreAndYear);
        }

        private final TextView nameTextView;
        private final TextView genreYearTextView;
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
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    private final List<MoviePreviewCard> dataset;
}
