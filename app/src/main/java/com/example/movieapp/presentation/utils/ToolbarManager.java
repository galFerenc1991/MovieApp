package com.example.movieapp.presentation.utils;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;


import com.example.movieapp.presentation.base.BaseActivity;

import org.androidannotations.annotations.EBean;

@EBean
public class ToolbarManager {

    private ActionBar actionBar;
    private Toolbar toolbar;
    private View.OnClickListener mNavigationClickListener;
    private float pxToolbarElevation;

    /**
     * Should be called after UI initialized
     */
    public void init(BaseActivity activity, Toolbar toolbar) {
        this.toolbar = toolbar;
        activity.setSupportActionBar(toolbar);
        actionBar = activity.getSupportActionBar();

        mNavigationClickListener = v -> activity.onBackPressed();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        Resources r = activity.getResources();
        pxToolbarElevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, r.getDisplayMetrics());
    }

    public void showHomeButton(boolean show) {
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(show);
        }
    }

    public void showToolbar(boolean isShow) {
        actionBar.show();
    }

    public void setTitle(@StringRes int title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void hideToolbar(boolean isHide) {
        actionBar.hide();
    }

    public void setTitle(CharSequence title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
