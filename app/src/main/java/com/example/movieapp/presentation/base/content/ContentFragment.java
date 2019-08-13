package com.example.movieapp.presentation.base.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.movieapp.R;
import com.example.movieapp.presentation.base.BaseFragment;
import com.example.movieapp.presentation.base.BasePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import javax.annotation.Nullable;


@EFragment
public abstract class ContentFragment extends BaseFragment implements ContentView {

    @ViewById
    protected ProgressBar pbMain_VC;
    @ViewById
    protected ProgressBar pbPagination_VC;

    @ViewById
    protected SwipeRefreshLayout swipeContainer_VC;
    @ViewById
    protected FrameLayout flContent_VC;

    protected abstract int getLayoutRes();

    protected abstract BasePresenter getPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.view_content, container, false);
        ViewGroup flContent = parent.findViewById(R.id.flContent_VC);
        View.inflate(getActivity(), getLayoutRes(), flContent);
        return parent;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getPresenter() != null) getPresenter().unsubscribe();
    }

    @AfterViews
    protected void afterViews() {
        swipeContainer_VC.setEnabled(false);
        swipeContainer_VC.setColorSchemeColors(ContextCompat.getColor(mActivity, R.color.colorPrimaryDark));
    }

    @Override
    public void showProgressMain() {
        hideKeyboard();
        dismissUI();
        pbMain_VC.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressPagination() {
        pbPagination_VC.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbMain_VC.setVisibility(View.GONE);
        pbPagination_VC.setVisibility(View.GONE);
        flContent_VC.setVisibility(View.VISIBLE);
    }

    private void dismissUI() {
        pbMain_VC.setVisibility(View.GONE);
        pbPagination_VC.setVisibility(View.GONE);
        flContent_VC.setVisibility(View.GONE);
    }
}