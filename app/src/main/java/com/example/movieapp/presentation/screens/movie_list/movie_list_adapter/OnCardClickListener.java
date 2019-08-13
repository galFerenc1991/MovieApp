package com.example.movieapp.presentation.screens.movie_list.movie_list_adapter;

import android.view.View;

public interface OnCardClickListener {
    void onClick(View view, int position, int viewType);
}