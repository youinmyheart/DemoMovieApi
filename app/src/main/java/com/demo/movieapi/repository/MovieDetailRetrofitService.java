package com.demo.movieapi.repository;

import com.demo.movieapi.model.MovieDetail;
import com.demo.movieapi.model.TMDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDetailRetrofitService {
    @GET("movie/{movie_id}")
    Call<MovieDetail> getMovieDetail(@Path("movie_id") int movieId, @Query("api_key") String apiKey,
                                     @Query("language") String language,
                                     @Query("append_to_response") String appendType);
}
