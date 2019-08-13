package com.example.movieapp.presentation.screens.movie_details;


import com.example.movieapp.data.api.exeptions.ConnectionLostException;
import com.example.movieapp.domain.movie_details_repository.MovieDetailsModel;
import com.example.movieapp.presentation.utils.ToastManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View mView;
    private MovieDetailsModel mMovieDetailsModel;
    private CompositeDisposable mCompositeDisposable;
    private int mMovieID;


    public MovieDetailPresenter(MovieDetailContract.View _view,
                                MovieDetailsModel _movieDetailsModel,
                                int _movieID) {
        this.mView = _view;
        this.mMovieDetailsModel = _movieDetailsModel;
        this.mCompositeDisposable = new CompositeDisposable();
        this.mMovieID = _movieID;
    }

    @Override
    public void subscribe() {
        mView.showProgressMain();
        loadMovieDetails();

    }

    private void loadMovieDetails() {
        mCompositeDisposable.add(mMovieDetailsModel.getMovieDetails(mMovieID)
                .subscribe(movieDetails -> {
                    mView.hideProgress();
                    mView.setMovieDetails(movieDetails);
                    mView.setMovieGenres(movieDetails.getGenres());
                }, throwableConsumer));
    }

    private Consumer<Throwable> throwableConsumer = throwable -> {
        throwable.printStackTrace();
        mView.hideProgress();
        mView.backToMovieListScreen();
        if (throwable instanceof ConnectionLostException) {
            ToastManager.showToast("Connection Lost");
        } else {
            ToastManager.showToast("Something went wrong");
        }
    };

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
