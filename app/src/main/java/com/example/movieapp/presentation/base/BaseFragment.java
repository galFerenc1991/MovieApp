package com.example.movieapp.presentation.base;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;

@EFragment
public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;

    @SystemService
    protected InputMethodManager inputMethodManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
        setHasOptionsMenu(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        hideKeyboard();
    }

    protected void hideKeyboard() {
        if (getView() != null) {
            inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

}

