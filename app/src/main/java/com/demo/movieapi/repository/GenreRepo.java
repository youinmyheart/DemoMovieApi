package com.demo.movieapi.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.GenreResponse;
import com.demo.movieapi.model.TMDBResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreRepo {

    private static final String TAG = GenreRepo.class.getSimpleName();

    public MutableLiveData<DataWrapper<GenreResponse>> getGenre() {
        final MutableLiveData<DataWrapper<GenreResponse>> liveData = new MutableLiveData<>();
        Call<GenreResponse> call = APIManager.getMovieGenre();
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                DataWrapper<GenreResponse> data = new DataWrapper<>(response.body(), DataWrapper.Status.SUCCESS, null);
                liveData.setValue(data);
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                DataWrapper<GenreResponse> data = new DataWrapper<>(null, DataWrapper.Status.ERROR, t.getMessage());
                liveData.setValue(data);
            }
        });
        return liveData;
    }
}
