package com.demo.movieapi.repository;

import com.demo.movieapi.model.TMDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PopularRetrofitService {
    @GET("movie/popular")
    Call<TMDBResponse> getPopular(@Query("api_key") String apiKey, @Query("language") String language,
                                   @Query("page") int page);
}
