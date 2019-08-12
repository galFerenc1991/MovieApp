package com.example.movieapp.data.model.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListResponse<T extends Parcelable> implements Parcelable {


    private int page;
    private List<T> results;
    @SerializedName("total_result")
    private int totalResult;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public List<T> getResults() {
        return results;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public int getTotalPages() {
        return totalPages;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        if (results == null || results.size() == 0)
            dest.writeInt(0);
        else {
            dest.writeInt(results.size());
            final Class<?> objectsType = results.get(0).getClass();
            dest.writeSerializable(objectsType);
            dest.writeList(results);
        }        dest.writeInt(this.totalResult);
        dest.writeInt(this.totalPages);
    }

    public ListResponse() {
    }

    protected ListResponse(Parcel in) {
        this.page = in.readInt();

        int size = in.readInt();
        if (size == 0) {
            results = null;
        } else {
            Class<?> type = (Class<?>) in.readSerializable();
            results = new ArrayList<>(size);
            in.readList(results, type.getClassLoader());
        }

        this.totalResult = in.readInt();
        this.totalPages = in.readInt();
    }

    public static final Creator<ListResponse> CREATOR = new Creator<ListResponse>() {
        @Override
        public ListResponse createFromParcel(Parcel source) {
            return new ListResponse(source);
        }

        @Override
        public ListResponse[] newArray(int size) {
            return new ListResponse[size];
        }
    };
}
