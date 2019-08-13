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

    private OnCardClickListener mOnCardClickListener;

    private List<Movie> mMovieList = new ArrayList<>();

    public MovieListAdapter() {
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        MovieListViewHolder movieListViewHolder = new MovieListViewHolder(view);
        if (mOnCardClickListener != null) {
            movieListViewHolder.setListeners(mOnCardClickListener);
        }
        return movieListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        Movie currentMovie = mMovieList.get(position);
        holder.tvTitle.setText(currentMovie.getOriginalTitle());
        holder.tvRate.setText(String.valueOf(currentMovie.getVoteCount()));
        holder.tvLike.setText(String.valueOf(currentMovie.getPopularity()));
        holder.tvOverview.setText(currentMovie.getOverview());

        Picasso.with(holder.itemView.getContext())
                .load(RestConstants.BASE_IMAGE_URL + RestConstants.IMAGE_URL_COMPONENT + currentMovie.getPosterPath())
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setMovieList(List<Movie> _list) {
        mMovieList.clear();
        mMovieList.addAll(_list);
        notifyDataSetChanged();
    }

    public void addMovieListDH(List<Movie> _list) {
        int oldSize = mMovieList.size();
        mMovieList.addAll(_list);
        notifyItemRangeInserted(oldSize, mMovieList.size());
    }

    public Movie getItem(int position) {
        if (0 <= position && position < mMovieList.size()) {
            return mMovieList.get(position);
        } else {
            return null;
        }
    }

    public void setOnCardClickListener(OnCardClickListener onCardClickListener) {
        this.mOnCardClickListener = onCardClickListener;
    }
}