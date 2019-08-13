package com.example.movieapp.presentation.base;

import android.os.Handler;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.movieapp.presentation.utils.ToolbarManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;

    @SystemService
    protected InputMethodManager inputMethodManager;
    @Bean
    protected ToolbarManager toolbarManager;

    @IdRes
    protected abstract int getContainer();

    protected abstract Toolbar getToolbar();

    @AfterViews
    public void initToolbar() {
        toolbarManager.init(this, getToolbar());

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                toolbarManager.showHomeButton(true);
            }
        });
    }

    public void replaceFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainer(), fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            if (doubleBackToExitPressedOnce) {
                finish();
            } else {
                doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        hideKeyboard();
    }

    protected void hideKeyboard() {
        if (getCurrentFocus() != null)
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    public ToolbarManager getToolbarManager() {
        return toolbarManager;
    }
}

