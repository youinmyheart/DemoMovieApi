package com.demo.movieapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.movieapi.Constants;
import com.demo.movieapi.ItemSpaceDecoration;
import com.demo.movieapi.OnClickViewHolderItemListener;
import com.demo.movieapi.R;
import com.demo.movieapi.adapter.CastRecyclerViewAdapter;
import com.demo.movieapi.adapter.MovieReviewRecyclerViewAdapter;
import com.demo.movieapi.adapter.RecommendationRecyclerViewAdapter;
import com.demo.movieapi.adapter.VideoRecyclerViewAdapter;
import com.demo.movieapi.model.CastCrew;
import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.MovieDetail;
import com.demo.movieapi.model.MovieReview;
import com.demo.movieapi.model.TMDBResponse;
import com.demo.movieapi.repository.APIManager;
import com.demo.movieapi.viewmodel.CastViewModel;
import com.demo.movieapi.viewmodel.MovieDetailViewModel;
import com.demo.movieapi.viewmodel.MovieReviewViewModel;
import com.demo.movieapi.viewmodel.RecommendationViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    private ImageView imvBack;
    private ImageView imvBackdrop;
    private ImageView imvPlayBackdrop;
    private ImageView imvPoster;
    private TextView tvMoviePoint;
    private LinearLayout containerStar;
    private ImageView imvStar1;
    private ImageView imvStar2;
    private ImageView imvStar3;
    private ImageView imvStar4;
    private ImageView imvStar5;
    private TextView tvMovieDate;
    private CardView cardGenre1;
    private TextView tvGenre1;
    private CardView cardGenre2;
    private TextView tvGenre2;
    private TextView tvMovieTitle;
    private TextView tvOverview;
    private TextView tvReadMore;
    private TextView tvFavorite;
    private LinearLayout containerRateStar;
    private ImageView imvRateStar1;
    private ImageView imvRateStar2;
    private ImageView imvRateStar3;
    private ImageView imvRateStar4;
    private ImageView imvRateStar5;
    private TextView tvNumberStar;

    private RecyclerView seriesCastView;
    private List<CastCrew.Cast> castList;
    private CastRecyclerViewAdapter castRecyclerViewAdapter;
    private LinearLayoutManager castLayoutManager;
    private CastViewModel castViewModel;

    private RecyclerView videoView;
    private List<MovieDetail.Video> videoList;
    private VideoRecyclerViewAdapter videoRecyclerViewAdapter;
    private LinearLayoutManager videoLayoutManager;

    private ImageView imvLoadMoreComments;
    private RecyclerView commentsView;
    private LinearLayoutManager commentsLayoutManager;
    private MovieReviewRecyclerViewAdapter movieReviewRecyclerViewAdapter;
    private List<MovieReview.ReviewDetail> reviewList;
    private MovieReviewViewModel movieReviewViewModel;

    private ImageView imvLoadMoreRecommendations;
    private RecyclerView recommendationsView;
    private LinearLayoutManager recommendationLayoutManager;
    private RecommendationRecyclerViewAdapter recommendationRecyclerViewAdapter;
    private List<TMDBResponse.Movie> recommendationList;
    private RecommendationViewModel recommendationViewModel;

    private int movieId;
    private MovieDetailViewModel movieDetailViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setUpView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void setUpView() {
        hideActionBar();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.movie_detail_activity);
        imvBack = findViewById(R.id.imv_back);
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imvBackdrop = findViewById(R.id.imvBackdrop);
        imvPlayBackdrop = findViewById(R.id.imvPlayBackdrop);
        imvPoster = findViewById(R.id.imvPoster);
        tvMoviePoint = findViewById(R.id.tv_movie_point);
        containerStar = findViewById(R.id.container_star);
        imvStar1 = findViewById(R.id.imv_star_1);
        imvStar2 = findViewById(R.id.imv_star_2);
        imvStar3 = findViewById(R.id.imv_star_3);
        imvStar4 = findViewById(R.id.imv_star_4);
        imvStar5 = findViewById(R.id.imv_star_5);
        tvMovieDate = findViewById(R.id.tv_movie_date);
        cardGenre1 = findViewById(R.id.card_genre_1);
        tvGenre1 = findViewById(R.id.tv_genre_1);
        cardGenre2 = findViewById(R.id.card_genre_2);
        tvGenre2 = findViewById(R.id.tv_genre_2);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvOverview = findViewById(R.id.tv_movie_overview);
        tvReadMore = findViewById(R.id.tv_read_more);
        containerRateStar = findViewById(R.id.container_rate_star);
        imvRateStar1 = findViewById(R.id.rate_star_1);
        imvRateStar2 = findViewById(R.id.rate_star_2);
        imvRateStar3 = findViewById(R.id.rate_star_3);
        imvRateStar4 = findViewById(R.id.rate_star_4);
        imvRateStar5 = findViewById(R.id.rate_star_5);

        seriesCastView = findViewById(R.id.series_cast_list);
        castLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        seriesCastView.setLayoutManager(castLayoutManager);
        seriesCastView.addItemDecoration(new ItemSpaceDecoration(5));

        castList = new ArrayList<>();
        castRecyclerViewAdapter = new CastRecyclerViewAdapter(this, castList);
        seriesCastView.setAdapter(castRecyclerViewAdapter);
        castViewModel = new CastViewModel();

        videoView = findViewById(R.id.video_list);
        videoLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        videoView.setLayoutManager(videoLayoutManager);
        videoView.addItemDecoration(new ItemSpaceDecoration(20));

        videoList = new ArrayList<>();
        videoRecyclerViewAdapter = new VideoRecyclerViewAdapter(this, videoList);
        videoView.setAdapter(videoRecyclerViewAdapter);

        imvLoadMoreComments = findViewById(R.id.imv_load_more_comments);
        commentsView = findViewById(R.id.comments_list);
        commentsLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        commentsView.setLayoutManager(commentsLayoutManager);

        reviewList = new ArrayList<>();
        movieReviewViewModel = new MovieReviewViewModel();
        movieReviewRecyclerViewAdapter = new MovieReviewRecyclerViewAdapter(this, reviewList, movieReviewViewModel);
        commentsView.setAdapter(movieReviewRecyclerViewAdapter);

        imvLoadMoreRecommendations = findViewById(R.id.imv_load_more_recommendations);
        recommendationsView = findViewById(R.id.recommendations_list);
        recommendationLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recommendationsView.setLayoutManager(recommendationLayoutManager);
        recommendationsView.addItemDecoration(new ItemSpaceDecoration(10));

        recommendationList = new ArrayList<>();
        recommendationViewModel = new RecommendationViewModel();
        recommendationRecyclerViewAdapter = new RecommendationRecyclerViewAdapter(this, recommendationList);
        recommendationRecyclerViewAdapter.setOnClickItemListener(new OnClickViewHolderItemListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
                Log.d(TAG, "recommendation onItemClick position: " + position);
                goToMovieDetailActivity(recommendationList.get(position).getId());
            }
        });
        recommendationsView.setAdapter(recommendationRecyclerViewAdapter);

        movieId = getIntent().getExtras().getInt(Constants.MOVIE_ID_KEY, -1);
        Log.d(TAG, "movieId: " + movieId);

        movieDetailViewModel = new MovieDetailViewModel();
        getMovieDetail(movieId);
        handleSeriesCast();
        handleMovieReview();
        handleRecommendation();
    }

    private void hideActionBar() {
        // place before setContentView()
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void loadImage(ImageView imv, String imagePath, Callback callback) {
        Picasso picasso = Picasso.with(this);
        picasso.setLoggingEnabled(true);
        picasso.load(imagePath).into(imv, callback);
    }

    private void getMovieDetail(int movieId) {
        imvPlayBackdrop.setVisibility(View.INVISIBLE);
        cardGenre1.setVisibility(View.INVISIBLE);
        cardGenre2.setVisibility(View.INVISIBLE);
        movieDetailViewModel.getMovieDetail(movieId).observe(this, new Observer<DataWrapper<MovieDetail>>() {
            @Override
            public void onChanged(DataWrapper<MovieDetail> movieDetailDataWrapper) {
                MovieDetail data = movieDetailDataWrapper.getData();
                switch (movieDetailDataWrapper.getStatus()) {
                    case SUCCESS:
                        if (data != null) {
                            handleMovieDetailData(data);
                        }
                        break;
                    case ERROR:
                        Log.d(TAG, "getMovieDetail error: " + movieDetailDataWrapper.getMessage());
                        break;
                }
            }
        });
    }

    private void handleMovieDetailData(MovieDetail data) {
        String backdropPath = APIManager.IMAGE_BASE_URL + "/w400" + data.getBackdropPath();
        loadImage(imvBackdrop, backdropPath, new Callback() {
            @Override
            public void onSuccess() {
                imvPlayBackdrop.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {
                Log.d(TAG, "onError imvBackdrop");
            }
        });

        String posterPath = APIManager.IMAGE_BASE_URL + "/w154" + data.getPosterPath();
        loadImage(imvPoster, posterPath, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Log.d(TAG, "onError imvPoster");
            }
        });

        tvMovieTitle.setText(data.getTitle());
        tvOverview.setText(data.getOverview());
        tvMoviePoint.setText(movieDetailViewModel.getRating());

        String genre1 = movieDetailViewModel.getGenre1();
        String genre2 = movieDetailViewModel.getGenre2();
        if (!TextUtils.isEmpty(genre1)) {
            cardGenre1.setVisibility(View.VISIBLE);
            tvGenre1.setText(genre1);
        }
        if (!TextUtils.isEmpty(genre2)) {
            cardGenre2.setVisibility(View.VISIBLE);
            tvGenre2.setText(genre2);
        }

        tvMovieDate.setText(movieDetailViewModel.getMovieDate());

        videoList.addAll(data.getVideoResponse().getVideos());
        videoRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void handleSeriesCast() {
        castViewModel.getMovieCast(movieId).observe(this, new Observer<DataWrapper<CastCrew>>() {
            @Override
            public void onChanged(DataWrapper<CastCrew> castCrewDataWrapper) {
                CastCrew response = castCrewDataWrapper.getData();
                switch (castCrewDataWrapper.getStatus()) {
                    case SUCCESS:
                        if (response != null) {
                            castList.addAll(response.getCasts());
                            Log.d(TAG, "castList size: " + castList.size());
                            castRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        break;
                    case ERROR:
                        Log.d(TAG, "getUpcoming error: " + castCrewDataWrapper.getMessage());
                        break;
                }
            }
        });
    }

    private void handleMovieReview() {
        movieReviewViewModel.getMovieReview(movieId, 1).observe(this, new Observer<DataWrapper<MovieReview>>() {
            @Override
            public void onChanged(DataWrapper<MovieReview> movieReviewDataWrapper) {
                MovieReview response = movieReviewDataWrapper.getData();
                switch (movieReviewDataWrapper.getStatus()) {
                    case SUCCESS:
                        if (response != null) {
                            reviewList.addAll(response.getReviewDetails());
                            Log.d(TAG, "reviewList size: " + reviewList.size());
                            movieReviewRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        break;
                    case ERROR:
                        Log.d(TAG, "getReviews error: " + movieReviewDataWrapper.getMessage());
                        break;
                }
            }
        });
    }

    private void handleRecommendation() {
        Log.d(TAG, "handleRecommendation");
        getRecommendationWithPage(1);
        recommendationsView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    // scroll horizontally
                    int visibleItemCount = recommendationLayoutManager.getChildCount();
                    Log.d(TAG, "recommendation visibleItemCount: " + visibleItemCount);
                    int totalItemCount = recommendationLayoutManager.getItemCount();
                    Log.d(TAG, "recommendation totalItemCount: " + totalItemCount);
                    int pastVisibleItems = recommendationLayoutManager.findFirstVisibleItemPosition();
                    Log.d(TAG, "recommendation pastVisibleItems: " + pastVisibleItems);
                    Log.d(TAG, "recommendation previousTotal: " + recommendationViewModel.getPreviousTotalItems());
                    if (recommendationViewModel.isLoading()) {
                        if (totalItemCount > recommendationViewModel.getPreviousTotalItems()) {
                            recommendationViewModel.setLoading(false);
                            recommendationViewModel.setPreviousTotalItems(totalItemCount);
                            recommendationViewModel.setCurrentPage(recommendationViewModel.getCurrentPage() + 1);
                        }
                    }
                    Log.d(TAG, "recommendation loading: " + recommendationViewModel.isLoading());
                    Log.d(TAG, "recommendation currentPage: " + recommendationViewModel.getCurrentPage());
                    // When no new pages are being loaded,
                    // but the user is at the end of the list, load the new page.
                    if (!recommendationViewModel.isLoading() && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        // Load the next page of the content in the background.
                        getRecommendationWithPage(recommendationViewModel.getCurrentPage() + 1);
                        recommendationViewModel.setLoading(true);
                    }
                }
            }
        });
    }

    private void getRecommendationWithPage(int page) {
        if (page < 1 || page > 1000) {
            Log.d(TAG, "Invalid page. Pages start at 1 and max at 1000");
            return;
        }
        recommendationViewModel.getRecommendation(movieId, page).observe(this, new Observer<DataWrapper<TMDBResponse>>() {
            @Override
            public void onChanged(DataWrapper<TMDBResponse> tmdbResponseDataWrapper) {
                TMDBResponse response = tmdbResponseDataWrapper.getData();
                switch (tmdbResponseDataWrapper.getStatus()) {
                    case SUCCESS:
                        if (response != null) {
                            recommendationList.addAll(response.getMovies());
                            Log.d(TAG, "recommendationList size: " + recommendationList.size());
                            recommendationRecyclerViewAdapter.notifyDataSetChanged();
                        }
                        break;
                    case ERROR:
                        Log.d(TAG, "getPopular error: " + tmdbResponseDataWrapper.getMessage());
                        recommendationViewModel.setLoading(false); // retry
                        break;
                }
            }
        });
    }

    private void goToMovieDetailActivity(int movieId) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_ID_KEY, movieId);
        startActivity(intent);
    }
}
