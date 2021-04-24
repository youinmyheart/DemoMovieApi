package com.demo.movieapi.repository;

import com.demo.movieapi.Constants;
import com.demo.movieapi.model.CastCrew;
import com.demo.movieapi.model.GenreResponse;
import com.demo.movieapi.model.MovieDetail;
import com.demo.movieapi.model.MovieReview;
import com.demo.movieapi.model.TMDBResponse;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APIManager {
    public static final String API_BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p";
    public static final String YOUTUBE_IMAGE_BASE_URL = "https://img.youtube.com/vi/";

    public static OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);
        return httpClient.build();
    }

    public static Retrofit getRetrofitService() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
    }

    public static Call<TMDBResponse> getTrendingMovieWeek(int page) {
        TrendingRetrofitService service = getRetrofitService().create(TrendingRetrofitService.class);
        return service.getTrending("movie", "week", Constants.API_KEY, page);
    }

    public static Call<TMDBResponse> getTrendingMovieDay(int page) {
        TrendingRetrofitService service = getRetrofitService().create(TrendingRetrofitService.class);
        return service.getTrending("movie", "day", Constants.API_KEY, page);
    }

    public static Call<GenreResponse> getMovieGenre() {
        GenreRetrofitService service = getRetrofitService().create(GenreRetrofitService.class);
        return service.getGenre("movie", Constants.API_KEY, "en-US");
    }

    public static Call<GenreResponse> getMovieTelevision() {
        GenreRetrofitService service = getRetrofitService().create(GenreRetrofitService.class);
        return service.getGenre("tv", Constants.API_KEY, "en-US");
    }

    public static Call<TMDBResponse> getPopularMovie(int page) {
        PopularRetrofitService service = getRetrofitService().create(PopularRetrofitService.class);
        return service.getPopular(Constants.API_KEY, "en-US", page);
    }

    public static Call<TMDBResponse> getTopRatedMovie(int page) {
        TopRatedRetrofitService service = getRetrofitService().create(TopRatedRetrofitService.class);
        return service.getTopRated(Constants.API_KEY, "en-US", page);
    }

    public static Call<TMDBResponse> getUpcomingMovie(int page) {
        UpcomingRetrofitService service = getRetrofitService().create(UpcomingRetrofitService.class);
        return service.getUpcoming(Constants.API_KEY, "en-US", page);
    }

    public static Call<MovieDetail> getMovieDetailWithVideo(int movieId) {
        MovieDetailRetrofitService service = getRetrofitService().create(MovieDetailRetrofitService.class);
        return service.getMovieDetail(movieId, Constants.API_KEY, "en-US", "videos");
    }

    public static Call<MovieReview> getMovieReview(int movieId, int page) {
        MovieReviewRetrofitService service = getRetrofitService().create(MovieReviewRetrofitService.class);
        return service.getMovieReview(movieId, Constants.API_KEY, "en-US", page);
    }

    public static Call<TMDBResponse> getMovieRecommendations(int movieId, int page) {
        RecommendationRetrofitService service = getRetrofitService().create(RecommendationRetrofitService.class);
        return service.getMovieRecommendations(movieId, Constants.API_KEY, "en-US", page);
    }

    public static Call<CastCrew> getMovieCast(int movieId) {
        CastRetrofitService service = getRetrofitService().create(CastRetrofitService.class);
        return service.getCastCrew(movieId, Constants.API_KEY, "en-US");
    }
}
