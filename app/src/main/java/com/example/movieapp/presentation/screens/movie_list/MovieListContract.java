package com.example.movieapp.presentation.screens.movie_list;

import com.example.movieapp.data.model.common.Movie;
import com.example.movieapp.presentation.base.BaseView;
import com.example.movieapp.presentation.base.content.ContentView;
import com.example.movieapp.presentation.base.refreshable.RefreshablePresenter;

import java.util.List;

public class MovieListContract {

    interface View extends ContentView, BaseView<Presenter> {
        void setMovieList(List<Movie> _movieList);

        void addMovieList(List<Movie> _movieList);

        void openMovieDetailScreen(int _movieID);

    }

    interface Presenter extends RefreshablePresenter {
        void loadNextPage();

        void clickedMovieItem(Movie _movie);
    }

}
