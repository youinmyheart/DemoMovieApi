package com.demo.movieapi.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.TMDBResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingRepo {
    private static final String TAG = UpcomingRepo.class.getSimpleName();

    public MutableLiveData<DataWrapper<TMDBResponse>> getUpcoming(int page) {
        final MutableLiveData<DataWrapper<TMDBResponse>> liveData = new MutableLiveData<>();
        Call<TMDBResponse> call = APIManager.getUpcomingMovie(page);
        call.enqueue(new Callback<TMDBResponse>() {
            @Override
            public void onResponse(Call<TMDBResponse> call, Response<TMDBResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                DataWrapper<TMDBResponse> data = new DataWrapper<>(response.body(), DataWrapper.Status.SUCCESS, null);
                liveData.setValue(data);
            }

            @Override
            public void onFailure(Call<TMDBResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
                DataWrapper<TMDBResponse> data = new DataWrapper<>(null, DataWrapper.Status.ERROR, t.getMessage());
                liveData.setValue(data);
            }
        });
        return liveData;
    }
}
