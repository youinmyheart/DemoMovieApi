package com.demo.movieapi.repository;

import com.demo.movieapi.model.TMDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecommendationRetrofitService {
    @GET("movie/{movie_id}/recommendations")
    Call<TMDBResponse> getMovieRecommendations(@Path("movie_id") int movieId, @Query("api_key") String apiKey,
                                               @Query("language") String language,
                                               @Query("page") int page);
}
