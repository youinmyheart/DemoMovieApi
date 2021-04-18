package com.demo.movieapi.repository;

import com.demo.movieapi.model.TMDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopRatedRetrofitService {
    @GET("movie/top_rated")
    Call<TMDBResponse> getTopRated(@Query("api_key") String apiKey, @Query("language") String language,
                                  @Query("page") int page);
}
