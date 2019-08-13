package com.example.movieapp.presentation.screens.movie_details.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.data.model.common.Genre;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean
public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Genre> mGenres = new ArrayList<>();

    public static class GenreViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public GenreViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.tvGenre_GI);
        }
    }

    public GenreAdapter() {
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_item, parent, false);
        return new GenreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GenreViewHolder holder, int position) {
        holder.textView.setText(mGenres.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    public void setGenreList(List<Genre> _genres) {
        mGenres = _genres;
        notifyDataSetChanged();
    }

}
