package com.demo.movieapi.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.GenreResponse;
import com.demo.movieapi.model.MovieDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailRepo {

    private static final String TAG = GenreRepo.class.getSimpleName();

    public MutableLiveData<DataWrapper<MovieDetail>> getMovieDetail(int movieId) {
        final MutableLiveData<DataWrapper<MovieDetail>> liveData = new MutableLiveData<>();
        Call<MovieDetail> call = APIManager.getMovieDetailWithVideo(movieId);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                Log.d(TAG, "onResponse: " + response.body());
                DataWrapper<MovieDetail> data = new DataWrapper<>(response.body(), DataWrapper.Status.SUCCESS, null);
                liveData.setValue(data);
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
                DataWrapper<MovieDetail> data = new DataWrapper<>(null, DataWrapper.Status.ERROR, t.getMessage());
                liveData.setValue(data);
            }
        });
        return liveData;
    }
}
