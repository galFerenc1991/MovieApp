package com.example.movieapp.presentation.base.refreshable;

import com.example.movieapp.presentation.base.content.ContentFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@EFragment
public abstract class RefreshableFragment extends ContentFragment {

    protected abstract RefreshablePresenter getPresenter();

    @AfterViews
    protected void initRefreshing() {
        swipeContainer_VC.setEnabled(true);
        swipeContainer_VC.setOnRefreshListener(() -> {
            getPresenter().onRefresh();
        });
    }

    public void enableRefreshing(boolean isEnabled) {
        swipeContainer_VC.setEnabled(isEnabled);
    }

    public boolean isRefreshing() {
        return swipeContainer_VC.isRefreshing();
    }

    @Override
    public void showProgressPagination() {
        super.showProgressPagination();
        enableRefreshing(false);
    }

    @Override
    public void showProgressMain() {
        super.showProgressMain();
        enableRefreshing(false);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        enableRefreshing(true);
        if (swipeContainer_VC.isRefreshing()) swipeContainer_VC.setRefreshing(false);
    }
}
