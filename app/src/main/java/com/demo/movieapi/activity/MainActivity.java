package com.demo.movieapi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import com.demo.movieapi.R;
import com.demo.movieapi.adapter.GenreRecyclerViewAdapter;
import com.demo.movieapi.adapter.TrendingRecyclerViewAdapter;
import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.GenreResponse;
import com.demo.movieapi.model.MovieDetail;
import com.demo.movieapi.model.MovieReview;
import com.demo.movieapi.repository.APIManager;
import com.demo.movieapi.model.TMDBResponse;
import com.demo.movieapi.viewmodel.GenreViewModel;
import com.demo.movieapi.viewmodel.TrendingViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView trendingView;
    private List<TMDBResponse.Movie> trendingList;
    private TrendingRecyclerViewAdapter trendingRecyclerViewAdapter;
    private LinearLayoutManager trendingLayoutManager;
    private TrendingViewModel trendingViewModel;

    private RecyclerView genreView;
    private List<GenreResponse.Genre> genreList;
    private GenreRecyclerViewAdapter genreRecyclerViewAdapter;
    private LinearLayoutManager genreLayoutManager;
    private GenreViewModel genreViewModel;

    private int currentPage = 0;
    private boolean loading = true;
    private int previousTotal = 0;
    private int pastVisibleItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setUpView();
//        handleTrending();
//        getGenre();
//        getPopular();
//        getTopRated();
//        getUpcoming();
//        getMovieDetail();
//        getMovieReview();
//        getMovieRecommendations();
    }

    private void setUpView() {
        hideActionBar();
        setContentView(R.layout.home_activity);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshViews();
            }
        });
        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                refreshViews();
            }
        });
//        swipeRefreshLayout.setColorSchemeResources(R.color.black,
//                android.R.color.holo_green_dark,
//                android.R.color.holo_orange_dark,
//                android.R.color.holo_blue_dark);
        ImageView imvLoadMore = findViewById(R.id.imv_load_more);

        trendingView = findViewById(R.id.trendingList);
        trendingLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        trendingView.setLayoutManager(trendingLayoutManager);

        trendingList = new ArrayList<>();
        trendingRecyclerViewAdapter = new TrendingRecyclerViewAdapter(this, trendingList);
        trendingView.setAdapter(trendingRecyclerViewAdapter);
        trendingViewModel = new TrendingViewModel();

        genreView = findViewById(R.id.genreList);
        genreLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        genreView.setLayoutManager(genreLayoutManager);

        genreList = new ArrayList<>();
        genreRecyclerViewAdapter = new GenreRecyclerViewAdapter(this, genreList);
        genreView.setAdapter(genreRecyclerViewAdapter);
        genreViewModel = new GenreViewModel();
    }

    private void hideActionBar() {
        // place before setContentView()
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void handleTrending() {
        Log.d(TAG, "handleTrending");
        getTrendingWithPage(1);
        trendingView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    // scroll horizontally
                    visibleItemCount = trendingLayoutManager.getChildCount();
                    Log.d(TAG, "visibleItemCount: " + visibleItemCount);
                    totalItemCount = trendingLayoutManager.getItemCount();
                    Log.d(TAG, "totalItemCount: " + totalItemCount);
                    pastVisibleItems = trendingLayoutManager.findFirstVisibleItemPosition();
                    Log.d(TAG, "pastVisibleItems: " + pastVisibleItems);
                    Log.d(TAG, "previousTotal: " + previousTotal);
                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                            currentPage++;
                        }
                    }
                    Log.d(TAG, "loading: " + loading);
                    Log.d(TAG, "currentPage: " + currentPage);
                    // When no new pages are being loaded,
                    // but the user is at the end of the list, load the new page.
                    if (!loading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        // Load the next page of the content in the background.
                        getTrendingWithPage(currentPage + 1);
                        loading = true;
                    }
                }
            }
        });
    }

    private void getTrendingWithPage(int page) {
        Log.d(TAG, "getTrendingWithPage: " + page);
        if (page < 1 || page > 1000) {
            Log.d(TAG, "Invalid page. Pages start at 1 and max at 1000");
            return;
        }
        trendingViewModel.getTrending(page).observe(this, new Observer<DataWrapper<TMDBResponse>>() {
            @Override
            public void onChanged(DataWrapper<TMDBResponse> response) {
                Log.d(TAG, "getTrendingWithPage onChanged");
                TMDBResponse dataResponse = response.getData();
                switch (response.getStatus()) {
                    case SUCCESS:
                        if (dataResponse != null) {
                            trendingList.addAll(dataResponse.getMovies());
                            trendingRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        break;
                    case ERROR:
                        Log.d(TAG, "getTrending error: " + response.getMessage());
                        loading = false; // retry
                        break;
                }
            }
        });
    }

    private void refreshViews() {
        Log.d(TAG, "refreshViews");
        previousTotal = 0;
        loading = true;
        currentPage = 0;
        trendingList.clear();
        trendingRecyclerViewAdapter.notifyDataSetChanged();
        genreList.clear();
        genreRecyclerViewAdapter.notifyDataSetChanged();
        handleTrending();
        handleGenre();

        MediatorLiveData<Boolean> mediatorLiveData = combineLiveData(trendingViewModel.getMutableLiveData(), genreViewModel.getMutableLiveData());
        mediatorLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean completed) {
                Log.d(TAG, "mediatorLiveData onChanged completed: " + completed);
                if (completed) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private MediatorLiveData<Boolean> combineLiveData(LiveData liveData1, LiveData liveData2) {
        final Object[] object1 = {null};
        final Object[] object2 = {null};
        MediatorLiveData<Boolean> mediatorLiveData = new MediatorLiveData<>();
        mediatorLiveData.addSource(liveData1, new Observer() {
            @Override
            public void onChanged(Object o) {
                object1[0] = o;
                boolean completed = object1[0] != null && object2[0] != null;
                mediatorLiveData.setValue(completed);
            }
        });
        mediatorLiveData.addSource(liveData2, new Observer() {
            @Override
            public void onChanged(Object o) {
                object2[0] = o;
                boolean completed = object1[0] != null && object2[0] != null;
                mediatorLiveData.setValue(completed);
            }
        });
        return mediatorLiveData;
    }

    private void handleGenre() {
        Log.d(TAG, "handleGenre");
        genreViewModel.getGenre().observe(this, new Observer<DataWrapper<GenreResponse>>() {
            @Override
            public void onChanged(DataWrapper<GenreResponse> genreResponseDataWrapper) {
                Log.d(TAG, "handleGenre onChanged");
                GenreResponse response = genreResponseDataWrapper.getData();
                switch (genreResponseDataWrapper.getStatus()) {
                    case SUCCESS:
                        if (response != null) {
                            // here if we use: genreList = response.getGenres(),
                            // recyclerViewAdapter will not be called because list is empty.
                            // At that time, we must init adapter again and set adapter to recycle view,
                            // or we set new list (not empty) to adapter
                            genreList.addAll(response.getGenres());
                            Log.d(TAG, "genreList size: " + genreList.size());
                            genreRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        break;
                    case ERROR:
                        Log.d(TAG, "getGenre error: " + genreResponseDataWrapper.getMessage());
                        break;
                }
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