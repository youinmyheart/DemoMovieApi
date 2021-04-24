package com.demo.movieapi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import com.demo.movieapi.Constants;
import com.demo.movieapi.ItemSpaceDecoration;
import com.demo.movieapi.OnClickViewHolderItemListener;
import com.demo.movieapi.R;
import com.demo.movieapi.Utils;
import com.demo.movieapi.adapter.GenreRecyclerViewAdapter;
import com.demo.movieapi.adapter.MovieRecyclerViewAdapter;
import com.demo.movieapi.adapter.TrendingRecyclerViewAdapter;
import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.GenreResponse;
import com.demo.movieapi.model.MovieDetail;
import com.demo.movieapi.model.MovieReview;
import com.demo.movieapi.repository.APIManager;
import com.demo.movieapi.model.TMDBResponse;
import com.demo.movieapi.viewmodel.GenreViewModel;
import com.demo.movieapi.viewmodel.PopularViewModel;
import com.demo.movieapi.viewmodel.TopRatedViewModel;
import com.demo.movieapi.viewmodel.TrendingViewModel;
import com.demo.movieapi.viewmodel.UpcomingViewModel;

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

    private RecyclerView popularView;
    private List<TMDBResponse.Movie> popularList;
    private MovieRecyclerViewAdapter popularRecyclerViewAdapter;
    private LinearLayoutManager popularLayoutManager;
    private PopularViewModel popularViewModel;

    private RecyclerView topRatedView;
    private List<TMDBResponse.Movie> topRatedList;
    private MovieRecyclerViewAdapter topRatedRecyclerViewAdapter;
    private LinearLayoutManager topRatedLayoutManager;
    private TopRatedViewModel topRatedViewModel;

    private RecyclerView upcomingView;
    private List<TMDBResponse.Movie> upcomingList;
    private MovieRecyclerViewAdapter upcomingRecyclerViewAdapter;
    private LinearLayoutManager upcomingLayoutManager;
    private UpcomingViewModel upcomingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setUpView();
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
        trendingView.addItemDecoration(new ItemSpaceDecoration(10));

        trendingList = new ArrayList<>();
        trendingRecyclerViewAdapter = new TrendingRecyclerViewAdapter(this, trendingList);
        trendingRecyclerViewAdapter.setOnClickItemListener(new OnClickViewHolderItemListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
                Log.d(TAG, "trending onItemClick position: " + position);
                goToMovieDetailActivity(trendingList.get(position).getId());
            }
        });
        trendingView.setAdapter(trendingRecyclerViewAdapter);
        trendingViewModel = new TrendingViewModel();

        genreView = findViewById(R.id.genreList);
        genreLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        genreView.setLayoutManager(genreLayoutManager);
        genreView.addItemDecoration(new ItemSpaceDecoration(10));

        genreList = new ArrayList<>();
        genreRecyclerViewAdapter = new GenreRecyclerViewAdapter(this, genreList);
        genreRecyclerViewAdapter.setOnClickItemListener(new OnClickViewHolderItemListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
                Log.d(TAG, "genre onItemClick position: " + position);
            }
        });
        genreView.setAdapter(genreRecyclerViewAdapter);
        genreViewModel = new GenreViewModel();

        popularView = findViewById(R.id.popularList);
        popularLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularView.setLayoutManager(popularLayoutManager);
        popularView.addItemDecoration(new ItemSpaceDecoration(10));

        popularList = new ArrayList<>();
        popularRecyclerViewAdapter = new MovieRecyclerViewAdapter(this, popularList);
        popularRecyclerViewAdapter.setOnClickItemListener(new OnClickViewHolderItemListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
                Log.d(TAG, "popular onItemClick position: " + position);
            }
        });
        popularView.setAdapter(popularRecyclerViewAdapter);
        popularViewModel = new PopularViewModel();

        topRatedView = findViewById(R.id.topRatedList);
        topRatedLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        topRatedView.setLayoutManager(topRatedLayoutManager);
        topRatedView.addItemDecoration(new ItemSpaceDecoration(10));

        topRatedList = new ArrayList<>();
        topRatedRecyclerViewAdapter = new MovieRecyclerViewAdapter(this, topRatedList);
        topRatedRecyclerViewAdapter.setOnClickItemListener(new OnClickViewHolderItemListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
                Log.d(TAG, "topRated onItemClick position: " + position);
            }
        });
        topRatedView.setAdapter(topRatedRecyclerViewAdapter);
        topRatedViewModel = new TopRatedViewModel();

        upcomingView = findViewById(R.id.upcomingList);
        upcomingLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        upcomingView.setLayoutManager(upcomingLayoutManager);
        upcomingView.addItemDecoration(new ItemSpaceDecoration(10));

        upcomingList = new ArrayList<>();
        upcomingRecyclerViewAdapter = new MovieRecyclerViewAdapter(this, upcomingList);
        upcomingRecyclerViewAdapter.setOnClickItemListener(new OnClickViewHolderItemListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
                Log.d(TAG, "upcoming onItemClick position: " + position);
            }
        });
        upcomingView.setAdapter(upcomingRecyclerViewAdapter);
        upcomingViewModel = new UpcomingViewModel();
    }

    private void goToMovieDetailActivity(int movieId) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_ID_KEY, movieId);
        startActivity(intent);
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
                    int visibleItemCount = trendingLayoutManager.getChildCount();
                    Log.d(TAG, "visibleItemCount: " + visibleItemCount);
                    int totalItemCount = trendingLayoutManager.getItemCount();
                    Log.d(TAG, "totalItemCount: " + totalItemCount);
                    int pastVisibleItems = trendingLayoutManager.findFirstVisibleItemPosition();
                    Log.d(TAG, "pastVisibleItems: " + pastVisibleItems);
                    Log.d(TAG, "previousTotal: " + trendingViewModel.getPreviousTotalItems());
                    if (trendingViewModel.isLoading()) {
                        if (totalItemCount > trendingViewModel.getPreviousTotalItems()) {
                            trendingViewModel.setLoading(false);
                            trendingViewModel.setPreviousTotalItems(totalItemCount);
                            trendingViewModel.setCurrentPage(trendingViewModel.getCurrentPage() + 1);
                        }
                    }
                    Log.d(TAG, "loading: " + trendingViewModel.isLoading());
                    Log.d(TAG, "currentPage: " + trendingViewModel.getCurrentPage());
                    // When no new pages are being loaded,
                    // but the user is at the end of the list, load the new page.
                    if (!trendingViewModel.isLoading() && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        // Load the next page of the content in the background.
                        getTrendingWithPage(trendingViewModel.getCurrentPage() + 1);
                        trendingViewModel.setLoading(true);
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
                        trendingViewModel.setLoading(false); // retry
                        break;
                }
            }
        });
    }

    private void refreshViews() {
        Log.d(TAG, "refreshViews");
        trendingViewModel.setPreviousTotalItems(0);
        trendingViewModel.setLoading(true);
        trendingViewModel.setCurrentPage(0);
        trendingList.clear();
        trendingRecyclerViewAdapter.notifyDataSetChanged();

        genreList.clear();
        genreRecyclerViewAdapter.notifyDataSetChanged();

        popularViewModel.setPreviousTotalItems(0);
        popularViewModel.setLoading(true);
        popularViewModel.setCurrentPage(0);
        popularList.clear();
        popularRecyclerViewAdapter.notifyDataSetChanged();

        topRatedViewModel.setPreviousTotalItems(0);
        topRatedViewModel.setLoading(true);
        topRatedViewModel.setCurrentPage(0);
        topRatedList.clear();
        topRatedRecyclerViewAdapter.notifyDataSetChanged();

        upcomingViewModel.setPreviousTotalItems(0);
        upcomingViewModel.setLoading(true);
        upcomingViewModel.setCurrentPage(0);
        upcomingList.clear();
        upcomingRecyclerViewAdapter.notifyDataSetChanged();

        handleTrending();
        handleGenre();
        handlePopular();
        handleTopRated();
        handleUpcoming();

        MediatorLiveData<Boolean> mediatorLiveData1 = Utils.combineLiveData(trendingViewModel.getMutableLiveData(), genreViewModel.getMutableLiveData());
        mediatorLiveData1.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean completed) {
                Log.d(TAG, "trending and genre onChanged completed: " + completed);
            }
        });

        MediatorLiveData<Boolean> mediatorLiveData2 = Utils.combineLiveData(mediatorLiveData1, popularViewModel.getMutableLiveData());
        mediatorLiveData2.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean completed) {
                Log.d(TAG, "mediatorLiveData1 and popular onChanged completed: " + completed);
            }
        });

        MediatorLiveData<Boolean> mediatorLiveData3 = Utils.combineLiveData(mediatorLiveData2, topRatedViewModel.getMutableLiveData());
        mediatorLiveData3.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean completed) {
                Log.d(TAG, "mediatorLiveData2 and topRated onChanged completed: " + completed);
            }
        });

        MediatorLiveData<Boolean> mediatorLiveData4 = Utils.combineLiveData(mediatorLiveData3, upcomingViewModel.getMutableLiveData());
        mediatorLiveData4.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean completed) {
                Log.d(TAG, "mediatorLiveData3 and upcoming onChanged completed: " + completed);
                if (completed) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
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

    private void handlePopular() {
        Log.d(TAG, "handlePopular");
        getPopularWithPage(1);
        popularView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    // scroll horizontally
                    int visibleItemCount = popularLayoutManager.getChildCount();
                    Log.d(TAG, "popular visibleItemCount: " + visibleItemCount);
                    int totalItemCount = popularLayoutManager.getItemCount();
                    Log.d(TAG, "popular totalItemCount: " + totalItemCount);
                    int pastVisibleItems = popularLayoutManager.findFirstVisibleItemPosition();
                    Log.d(TAG, "popular pastVisibleItems: " + pastVisibleItems);
                    Log.d(TAG, "popular previousTotal: " + popularViewModel.getPreviousTotalItems());
                    if (popularViewModel.isLoading()) {
                        if (totalItemCount > popularViewModel.getPreviousTotalItems()) {
                            popularViewModel.setLoading(false);
                            popularViewModel.setPreviousTotalItems(totalItemCount);
                            popularViewModel.setCurrentPage(popularViewModel.getCurrentPage() + 1);
                        }
                    }
                    Log.d(TAG, "popular loading: " + popularViewModel.isLoading());
                    Log.d(TAG, "popular currentPage: " + popularViewModel.getCurrentPage());
                    // When no new pages are being loaded,
                    // but the user is at the end of the list, load the new page.
                    if (!popularViewModel.isLoading() && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        // Load the next page of the content in the background.
                        getPopularWithPage(popularViewModel.getCurrentPage() + 1);
                        popularViewModel.setLoading(true);
                    }
                }
            }
        });
    }

    private void getPopularWithPage(int page) {
        if (page < 1 || page > 1000) {
            Log.d(TAG, "Invalid page. Pages start at 1 and max at 1000");
            return;
        }
        popularViewModel.getPopular(page).observe(this, new Observer<DataWrapper<TMDBResponse>>() {
            @Override
            public void onChanged(DataWrapper<TMDBResponse> tmdbResponseDataWrapper) {
                TMDBResponse response = tmdbResponseDataWrapper.getData();
                switch (tmdbResponseDataWrapper.getStatus()) {
                    case SUCCESS:
                        if (response != null) {
                            popularList.addAll(response.getMovies());
                            Log.d(TAG, "popularList size: " + popularList.size());
                            popularRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        break;
                    case ERROR:
                        Log.d(TAG, "getPopular error: " + tmdbResponseDataWrapper.getMessage());
                        popularViewModel.setLoading(false); // retry
                        break;
                }
            }
        });
    }

    private void handleTopRated() {
        getTopRatedWithPage(1);
        topRatedView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    // scroll horizontally
                    int visibleItemCount = topRatedLayoutManager.getChildCount();
                    Log.d(TAG, "topRated visibleItemCount: " + visibleItemCount);
                    int totalItemCount = topRatedLayoutManager.getItemCount();
                    Log.d(TAG, "topRated totalItemCount: " + totalItemCount);
                    int pastVisibleItems = topRatedLayoutManager.findFirstVisibleItemPosition();
                    Log.d(TAG, "topRated pastVisibleItems: " + pastVisibleItems);
                    Log.d(TAG, "topRated previousTotal: " + topRatedViewModel.getPreviousTotalItems());
                    if (topRatedViewModel.isLoading()) {
                        if (totalItemCount > topRatedViewModel.getPreviousTotalItems()) {
                            topRatedViewModel.setLoading(false);
                            topRatedViewModel.setPreviousTotalItems(totalItemCount);
                            topRatedViewModel.setCurrentPage(topRatedViewModel.getCurrentPage() + 1);
                        }
                    }
                    Log.d(TAG, "topRated loading: " + topRatedViewModel.isLoading());
                    Log.d(TAG, "topRated currentPage: " + topRatedViewModel.getCurrentPage());
                    // When no new pages are being loaded,
                    // but the user is at the end of the list, load the new page.
                    if (!topRatedViewModel.isLoading() && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        // Load the next page of the content in the background.
                        getTopRatedWithPage(topRatedViewModel.getCurrentPage() + 1);
                        topRatedViewModel.setLoading(true);
                    }
                }
            }
        });
    }

    private void getTopRatedWithPage(int page) {
        if (page < 1 || page > 1000) {
            Log.d(TAG, "Invalid page. Pages start at 1 and max at 1000");
            return;
        }
        topRatedViewModel.getTopRated(page).observe(this, new Observer<DataWrapper<TMDBResponse>>() {
            @Override
            public void onChanged(DataWrapper<TMDBResponse> tmdbResponseDataWrapper) {
                TMDBResponse response = tmdbResponseDataWrapper.getData();
                switch (tmdbResponseDataWrapper.getStatus()) {
                    case SUCCESS:
                        if (response != null) {
                            topRatedList.addAll(response.getMovies());
                            Log.d(TAG, "topRatedList size: " + topRatedList.size());
                            topRatedRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        break;
                    case ERROR:
                        Log.d(TAG, "getTopRated error: " + tmdbResponseDataWrapper.getMessage());
                        topRatedViewModel.setLoading(false); // retry
                        break;
                }
            }
        });
    }

    private void handleUpcoming() {
        getUpcomingWithPage(1);
        upcomingView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    // scroll horizontally
                    int visibleItemCount = upcomingLayoutManager.getChildCount();
                    Log.d(TAG, "upcoming visibleItemCount: " + visibleItemCount);
                    int totalItemCount = upcomingLayoutManager.getItemCount();
                    Log.d(TAG, "upcoming totalItemCount: " + totalItemCount);
                    int pastVisibleItems = upcomingLayoutManager.findFirstVisibleItemPosition();
                    Log.d(TAG, "upcoming pastVisibleItems: " + pastVisibleItems);
                    Log.d(TAG, "upcoming previousTotal: " + upcomingViewModel.getPreviousTotalItems());
                    if (upcomingViewModel.isLoading()) {
                        if (totalItemCount > upcomingViewModel.getPreviousTotalItems()) {
                            upcomingViewModel.setLoading(false);
                            upcomingViewModel.setPreviousTotalItems(totalItemCount);
                            upcomingViewModel.setCurrentPage(upcomingViewModel.getCurrentPage() + 1);
                        }
                    }
                    Log.d(TAG, "upcoming loading: " + upcomingViewModel.isLoading());
                    Log.d(TAG, "upcoming currentPage: " + upcomingViewModel.getCurrentPage());
                    // When no new pages are being loaded,
                    // but the user is at the end of the list, load the new page.
                    if (!upcomingViewModel.isLoading() && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        // Load the next page of the content in the background.
                        getUpcomingWithPage(upcomingViewModel.getCurrentPage() + 1);
                        upcomingViewModel.setLoading(true);
                    }
                }
            }
        });
    }

    private void getUpcomingWithPage(int page) {
        if (page < 1 || page > 1000) {
            Log.d(TAG, "Invalid page. Pages start at 1 and max at 1000");
            return;
        }
        upcomingViewModel.getUpcoming(page).observe(this, new Observer<DataWrapper<TMDBResponse>>() {
            @Override
            public void onChanged(DataWrapper<TMDBResponse> tmdbResponseDataWrapper) {
                TMDBResponse response = tmdbResponseDataWrapper.getData();
                switch (tmdbResponseDataWrapper.getStatus()) {
                    case SUCCESS:
                        if (response != null) {
                            upcomingList.addAll(response.getMovies());
                            Log.d(TAG, "upcomingList size: " + upcomingList.size());
                            upcomingRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        break;
                    case ERROR:
                        Log.d(TAG, "getUpcoming error: " + tmdbResponseDataWrapper.getMessage());
                        upcomingViewModel.setLoading(false); // retry
                        break;
                }
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