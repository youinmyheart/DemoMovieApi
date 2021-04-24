package com.demo.movieapi.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.demo.movieapi.model.CastCrew;
import com.demo.movieapi.model.DataWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastRepo {
    private static final String TAG = CastRepo.class.getSimpleName();

    public MutableLiveData<DataWrapper<CastCrew>> getMovieCast(int movieId) {
        final MutableLiveData<DataWrapper<CastCrew>> liveData = new MutableLiveData<>();
        Call<CastCrew> call = APIManager.getMovieCast(movieId);
        call.enqueue(new Callback<CastCrew>() {
            @Override
            public void onResponse(Call<CastCrew> call, Response<CastCrew> response) {
                Log.d(TAG, "onResponse: " + response.body());
                DataWrapper<CastCrew> data = new DataWrapper<>(response.body(), DataWrapper.Status.SUCCESS, null);
                liveData.setValue(data);
            }

            @Override
            public void onFailure(Call<CastCrew> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                DataWrapper<CastCrew> data = new DataWrapper<>(null, DataWrapper.Status.ERROR, t.getMessage());
                liveData.setValue(data);
            }
        });
        return liveData;
    }
}
