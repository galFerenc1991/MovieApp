package com.example.movieapp.presentation.screens.movie_list.movie_list_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.data.api.RestConstants;
import com.example.movieapp.data.model.common.Movie;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

@EBean
public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

    private List<Movie> mMovie = new ArrayList<>();

    public MovieListAdapter() {
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        Movie currentMovie = mMovie.get(position);
        holder.tvTitle.setText(currentMovie.getTitle());
        holder.tvRate.setText(String.valueOf(currentMovie.getVoteCount()));
        holder.tvLike.setText(String.valueOf(currentMovie.getPopularity()));
        holder.tvOverview.setText(currentMovie.getOverview());

        Picasso.with(holder.itemView.getContext())
                .load(RestConstants.BASE_IMAGE_URL + RestConstants.IMAGE_URL_COMPONENT + currentMovie.getPosterPath())
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return mMovie.size();
    }

    public void setMovieList(List<Movie> _list) {
        mMovie.clear();
        mMovie.addAll(_list);
        notifyDataSetChanged();
    }

    public void addMovieListDH(List<Movie> _list) {
        int oldSize = mMovie.size();
        mMovie.addAll(_list);
        notifyItemRangeInserted(oldSize, mMovie.size());

    }
}