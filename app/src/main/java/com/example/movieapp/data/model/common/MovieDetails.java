package com.example.movieapp.data.model.common;

import android.os.Parcel;

import java.util.List;

public class MovieDetails extends Movie {
    private List<Genre> genres;

    public List<Genre> getGenres() {
        return genres;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.genres);
    }

    public MovieDetails() {
    }

    protected MovieDetails(Parcel in) {
        super(in);
        this.genres = in.createTypedArrayList(Genre.CREATOR);
    }

    public static final Creator<MovieDetails> CREATOR = new Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel source) {
            return new MovieDetails(source);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };
}
