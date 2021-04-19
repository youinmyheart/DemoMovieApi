package com.demo.movieapi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import com.demo.movieapi.R;
import com.demo.movieapi.adapter.TrendingRecyclerViewAdapter;
import com.demo.movieapi.model.GenreResponse;
import com.demo.movieapi.model.MovieDetail;
import com.demo.movieapi.model.MovieReview;
import com.demo.movieapi.repository.APIManager;
import com.demo.movieapi.model.TMDBResponse;
import com.demo.movieapi.viewmodel.TrendingViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView trendingView;
    private List<TMDBResponse.Movie> trendingList;
    private TrendingRecyclerViewAdapter trendingRecyclerViewAdapter;

    private TrendingViewModel trendingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        hideActionBar();
        setContentView(R.layout.home_activity);
        setUpView();
        getTrending();
//        getGenre();
//        getPopular();
//        getTopRated();
//        getUpcoming();
//        getMovieDetail();
//        getMovieReview();
//        getMovieRecommendations();
    }

    private void setUpView() {
        ImageView imvLoadMore = findViewById(R.id.imv_load_more);

        trendingView = findViewById(R.id.trendingList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        trendingView.setLayoutManager(linearLayoutManager);

        trendingList = new ArrayList<>();
        trendingRecyclerViewAdapter = new TrendingRecyclerViewAdapter(MainActivity.this, trendingList);
        trendingView.setAdapter(trendingRecyclerViewAdapter);
        trendingViewModel = new TrendingViewModel();
    }

    private void hideActionBar() {
        // place before setContentView()
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void getTrending() {
        trendingViewModel.getTrending().observe(this, new Observer<List<TMDBResponse.Movie>>() {
            @Override
            public void onChanged(List<TMDBResponse.Movie> movies) {
                Log.d(TAG, "onChanged movies: " + movies.size());
                trendingList.addAll(movies);
                trendingRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getGenre() {
        Call<GenreResponse> call = APIManager.getMovieGenre();
        call.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                GenreResponse res = response.body();
                Log.d(TAG, "genre id: " + res.getGenres().get(0).getId());
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

    private void getPopular() {
        Call<TMDBResponse> call = APIManager.getPopularMovie(1);
        call.enqueue(new Callback<TMDBResponse>() {
            @Override
            public void onResponse(Call<TMDBResponse> call, Response<TMDBResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                TMDBResponse res = response.body();
                Log.d(TAG, "movie id: " + res.getMovies().get(0).getId());
            }

            @Override
            public void onFailure(Call<TMDBResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

    private void getTopRated() {
        Call<TMDBResponse> call = APIManager.getTopRatedMovie(1);
        call.enqueue(new Callback<TMDBResponse>() {
            @Override
            public void onResponse(Call<TMDBResponse> call, Response<TMDBResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                TMDBResponse res = response.body();
                Log.d(TAG, "movie id: " + res.getMovies().get(0).getId());
            }

            @Override
            public void onFailure(Call<TMDBResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

    private void getUpcoming() {
        Call<TMDBResponse> call = APIManager.getUpcomingMovie(1);
        call.enqueue(new Callback<TMDBResponse>() {
            @Override
            public void onResponse(Call<TMDBResponse> call, Response<TMDBResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                TMDBResponse res = response.body();
                Log.d(TAG, "movie id: " + res.getMovies().get(0).getId());
            }

            @Override
            public void onFailure(Call<TMDBResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

    private void getMovieDetail() {
        Call<MovieDetail> call = APIManager.getMovieDetailWithVideo(399566);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                Log.d(TAG, "onResponse: " + response.body());
                MovieDetail res = response.body();
                Log.d(TAG, "movie ImdbId: " + res.getImdbId());
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

    private void getMovieReview() {
        Call<MovieReview> call = APIManager.getMovieReview(399566, 1);
        call.enqueue(new Callback<MovieReview>() {
            @Override
            public void onResponse(Call<MovieReview> call, Response<MovieReview> response) {
                Log.d(TAG, "onResponse: " + response.body());
                MovieReview res = response.body();
                Log.d(TAG, "movieId: " + res.getMovieId());
            }

            @Override
            public void onFailure(Call<MovieReview> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }

    private void getMovieRecommendations() {
        Call<TMDBResponse> call = APIManager.getMovieRecommendations(399566, 1);
        call.enqueue(new Callback<TMDBResponse>() {
            @Override
            public void onResponse(Call<TMDBResponse> call, Response<TMDBResponse> response) {
                Log.d(TAG, "onResponse: " + response.body());
                TMDBResponse res = response.body();
                Log.d(TAG, "getMovies size: " + res.getMovies().size());
            }

            @Override
            public void onFailure(Call<TMDBResponse> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });
    }
}