package com.demo.movieapi.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.MovieReview;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieReviewRepo {
    private static final String TAG = MovieReviewRepo.class.getSimpleName();

    public MutableLiveData<DataWrapper<MovieReview>> getReviews(int movieId, int page) {
        final MutableLiveData<DataWrapper<MovieReview>> liveData = new MutableLiveData<>();
        Call<MovieReview> call = APIManager.getMovieReview(movieId, page);
        call.enqueue(new Callback<MovieReview>() {
            @Override
            public void onResponse(Call<MovieReview> call, Response<MovieReview> response) {
                Log.d(TAG, "onResponse: " + response.body());
                DataWrapper<MovieReview> data = new DataWrapper<>(response.body(), DataWrapper.Status.SUCCESS, null);
                liveData.setValue(data);
            }

            @Override
            public void onFailure(Call<MovieReview> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                DataWrapper<MovieReview> data = new DataWrapper<>(null, DataWrapper.Status.ERROR, t.getMessage());
                liveData.setValue(data);
            }
        });
        return liveData;
    }
}
