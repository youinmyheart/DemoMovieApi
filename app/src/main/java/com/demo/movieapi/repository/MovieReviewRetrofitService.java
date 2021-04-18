package com.demo.movieapi.repository;

import com.demo.movieapi.model.MovieDetail;
import com.demo.movieapi.model.MovieReview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieReviewRetrofitService {
    @GET("movie/{movie_id}/reviews")
    Call<MovieReview> getMovieReview(@Path("movie_id") int movieId, @Query("api_key") String apiKey,
                                     @Query("language") String language,
                                     @Query("page") int page);
}
