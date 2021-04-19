package com.demo.movieapi.repository;

import android.util.DisplayMetrics;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.demo.movieapi.activity.MainActivity;
import com.demo.movieapi.adapter.TrendingRecyclerViewAdapter;
import com.demo.movieapi.model.TMDBResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendingRepo {
    private static final String TAG = TrendingRepo.class.getSimpleName();

    public MutableLiveData<List<TMDBResponse.Movie>> getTrending() {
        Log.d(TAG, "getTrending");
        final MutableLiveData<List<TMDBResponse.Movie>> liveData = new MutableLiveData<>();
        Call<TMDBResponse> call = APIManager.getTrendingMovieWeek(1);
        call.enqueue(new Callback<TMDBResponse>() {
            @Override
            public void onResponse(Call<TMDBResponse> call, Response<TMDBResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                if (response.body() != null) {
                    liveData.setValue(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<TMDBResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
        return liveData;
    }
}
