package com.example.movieapp.presentation.screens.movie_details;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.data.api.RestConstants;
import com.example.movieapp.data.model.common.Genre;
import com.example.movieapp.data.model.common.MovieDetails;
import com.example.movieapp.domain.movie_details_repository.MovieDetailsRepository;
import com.example.movieapp.presentation.base.BasePresenter;
import com.example.movieapp.presentation.base.content.ContentFragment;
import com.example.movieapp.presentation.screens.movie_details.adapter.GenreAdapter;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment
public class MovieDetailFragment extends ContentFragment implements MovieDetailContract.View {

    @Bean
    protected MovieDetailsRepository mMovieDetailsRepository;
    @FragmentArg
    protected int mMovieID;
    private MovieDetailContract.Presenter mPresenter;
    @ViewById(R.id.ivMoviePoster_FMD)
    protected ImageView ivMoviePoster;
    @ViewById(R.id.tvMovieName_FMD)
    protected TextView tvMovieName;
    @ViewById(R.id.tvRate_FMD)
    protected TextView tvRate;
    @ViewById(R.id.tvLike_FMD)
    protected TextView tvLike;
    @ViewById(R.id.tvLanguage_FMD)
    protected TextView tvLanguage;
    @ViewById(R.id.tvOverview_FMD)
    protected TextView tvOverview;
    @ViewById(R.id.rvGenre_FMD)
    protected RecyclerView rvGenre;

    @Bean
    protected GenreAdapter mGenreAdapter;


    @AfterInject
    @Override
    public void initPresenter() {
        mPresenter = new MovieDetailPresenter(this, mMovieDetailsRepository, mMovieID);
    }

    @AfterViews
    protected void initUI() {
        mActivity.getToolbarManager().hideToolbar(true);
        mPresenter.subscribe();

        rvGenre.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvGenre.setAdapter(mGenreAdapter);
    }

    @Override
    public void setPresenter(MovieDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_movie_details;
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }


    @Override
    public void setMovieDetails(MovieDetails _movieDetails) {

        tvMovieName.setText(_movieDetails.getTitle());
        tvRate.setText(String.valueOf(_movieDetails.getVoteCount()));
        tvLike.setText(String.valueOf(_movieDetails.getPopularity()));
        tvLanguage.setText(_movieDetails.getOriginalLanguage());
        tvOverview.setText(_movieDetails.getOverview());

        Picasso.with(getContext())
                .load(RestConstants.BASE_IMAGE_URL + RestConstants.IMAGE_URL_COMPONENT + _movieDetails.getBackdropPath())
                .fit()
                .centerCrop()
                .into(ivMoviePoster);
    }

    @Override
    public void setMovieGenres(List<Genre> _genres) {
        mGenreAdapter.setGenreList(_genres);
    }


    @Override
    public void backToMovieListScreen() {
        mActivity.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
