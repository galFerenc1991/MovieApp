package com.example.movieapp.presentation.screens.movie_list.movie_list_adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;

public class MovieListViewHolder extends RecyclerView.ViewHolder {

    private CardView cvRootContainer;

    public ImageView ivPoster;
    public TextView tvTitle;
    public TextView tvRate;
    public TextView tvLike;
    public TextView tvOverview;

    public MovieListViewHolder(View _view) {
        super(_view);
        cvRootContainer = _view.findViewById(R.id.cvContainer_MLI);
        ivPoster = _view.findViewById(R.id.ivMoviePoster_MLI);
        tvTitle = _view.findViewById(R.id.tvMovieName_MLI);
        tvRate = _view.findViewById(R.id.tvRate_MLI);
        tvLike = _view.findViewById(R.id.tvLike_MLI);
        tvOverview = _view.findViewById(R.id.tvOverview_MLI);
    }

    public void setListeners(OnCardClickListener listener) {
        cvRootContainer.setOnClickListener(view -> listener.onClick(itemView, getAdapterPosition(), getItemViewType()));
    }
}
