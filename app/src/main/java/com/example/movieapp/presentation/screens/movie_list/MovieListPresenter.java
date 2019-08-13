package com.example.movieapp.presentation.screens.movie_list;

import com.example.movieapp.data.model.common.Movie;
import com.example.movieapp.domain.movie_list_reposytory.MovieListModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListPresenter implements MovieListContract.Presenter {

    private MovieListContract.View mView;
    private MovieListModel mMovieListModel;
    private CompositeDisposable mCompositeDisposable;
    private List<Movie> mLoadedMovies;

    private int mPage;
    private int mTotalPages = Integer.MAX_VALUE;
    private boolean mNeedRefresh;

    public MovieListPresenter(MovieListContract.View _view
            , MovieListModel _movieListModel) {
        this.mView = _view;
        this.mMovieListModel = _movieListModel;
        this.mCompositeDisposable = new CompositeDisposable();
        this.mLoadedMovies = new ArrayList<>();

        this.mPage = 1;
        mNeedRefresh = true;

        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if (mNeedRefresh) {
            mView.showProgressMain();
            loadMovieList(1);
        }
    }

    private void loadMovieList(int _page) {
        mCompositeDisposable.add(mMovieListModel.getMovieList(_page)
                .subscribe(movieListResponse -> {
                    mView.hideProgress();
                    this.mPage = _page;
                    mTotalPages = movieListResponse.getTotalPages();

                    if (mPage == 1) {
                        mView.setMovieList(movieListResponse.getResults());
                        mNeedRefresh = movieListResponse.getResults().isEmpty();
                        if (movieListResponse.getResults().isEmpty()) {
//                            mView.showPlaceHolderText();
                        }
                    } else {
                        mView.addMovieList(movieListResponse.getResults());
                    }
                }));
    }

    @Override
    public void loadNextPage() {
        if (mPage < mTotalPages) {
            mView.showProgressPagination();
            loadMovieList(mPage + 1);
        }
    }

    @Override
    public void onRefresh() {
        mLoadedMovies.clear();
        loadMovieList(1);
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
