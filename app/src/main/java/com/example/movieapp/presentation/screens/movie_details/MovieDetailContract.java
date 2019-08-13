package com.example.movieapp.presentation.screens.movie_details;

import com.example.movieapp.data.model.common.Genre;
import com.example.movieapp.data.model.common.MovieDetails;
import com.example.movieapp.presentation.base.BasePresenter;
import com.example.movieapp.presentation.base.BaseView;
import com.example.movieapp.presentation.base.content.ContentView;

import java.util.List;

public interface MovieDetailContract {

    interface View extends ContentView, BaseView<Presenter> {
        void setMovieDetails(MovieDetails _movieDetails);

        void setMovieGenres(List<Genre> _genres);

        void backToMovieListScreen();
    }

    interface Presenter extends BasePresenter {

    }
}
