package com.demo.movieapi.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.MovieDetail;
import com.demo.movieapi.repository.MovieDetailRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDetailViewModel extends ViewModel {

    private static final String TAG = MovieDetailViewModel.class.getSimpleName();
    private MovieDetailRepo movieDetailRepo;
    private MutableLiveData<DataWrapper<MovieDetail>> mutableLiveData;

    public MovieDetailViewModel() {
        movieDetailRepo = new MovieDetailRepo();
    }

    public MutableLiveData<DataWrapper<MovieDetail>> getMutableLiveData() {
        return mutableLiveData;
    }

    public MutableLiveData<DataWrapper<MovieDetail>> getMovieDetail(int movieId) {
        mutableLiveData = movieDetailRepo.getMovieDetail(movieId);
        return mutableLiveData;
    }

    public String getRating() {
        if (mutableLiveData == null || mutableLiveData.getValue() == null) {
            return "";
        }
        MovieDetail data = mutableLiveData.getValue().getData();
        if (data == null) {
            return "";
        }

        float ratingPoint = data.getVoteAverage();
        if (ratingPoint > 10) {
            ratingPoint = 10;
        } else if (ratingPoint < 0) {
            ratingPoint = 0;
        }
        return String.format(Locale.US,"%.1f", ratingPoint / 2);
    }

    public String getGenre1() {
        if (mutableLiveData == null || mutableLiveData.getValue() == null) {
            return "";
        }
        MovieDetail data = mutableLiveData.getValue().getData();
        if (data == null) {
            return "";
        }

        String genre1 = null;
        if (data.getGenres().size() >= 1) {
            genre1 = data.getGenres().get(0).getName();
        }
        return genre1;
    }

    public String getGenre2() {
        if (mutableLiveData == null || mutableLiveData.getValue() == null) {
            return "";
        }
        MovieDetail data = mutableLiveData.getValue().getData();
        if (data == null) {
            return "";
        }

        String genre2 = null;
        if (data.getGenres().size() >= 2) {
            genre2 = data.getGenres().get(1).getName();
        }
        return genre2;
    }

    public String getMovieDate() {
        if (mutableLiveData == null || mutableLiveData.getValue() == null) {
            return "";
        }
        MovieDetail data = mutableLiveData.getValue().getData();
        if (data == null) {
            return "";
        }

        String releaseDate = data.getReleaseDate();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            Date newDate = dateFormat.parse(data.getReleaseDate());
            dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);
            releaseDate = dateFormat.format(newDate);
        } catch (Exception e) {
            Log.d(TAG, "date format error: " + e.getMessage());
        }
        return releaseDate;
    }
}
