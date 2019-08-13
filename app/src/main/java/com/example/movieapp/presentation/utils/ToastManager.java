package com.example.movieapp.presentation.utils;

import android.widget.Toast;

import com.example.movieapp.MovieApp_;

public class ToastManager {

    private static Toast mToast = Toast.makeText(MovieApp_.getInstance().getApplicationContext(), "", Toast.LENGTH_SHORT);

    public static void showToast(CharSequence message) {
        mToast.setText(message);
        mToast.show();
    }

}
