package com.example.movieapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.movieapp.presentation.base.BaseActivity;
import com.example.movieapp.presentation.screens.movie_list.MovieListFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    @ViewById
    protected Toolbar toolbar_AD;
    @ViewById
    protected DrawerLayout drawerLayout_AD;
    @ViewById
    protected RelativeLayout rlNavigation_AD;

    @Override
    protected int getContainer() {
        return R.id.flFragmentContent_AD;
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar_AD;
    }

    @AfterViews
    protected void initUI() {
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout_AD, toolbar_AD, R.string.drower_open, R.string.drower_close);
        drawerLayout_AD.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        toolbarManager.showHomeButton(true);
    }

    protected void initFragment() {
        replaceFragment(MovieListFragment_.builder().build());
    }
}
