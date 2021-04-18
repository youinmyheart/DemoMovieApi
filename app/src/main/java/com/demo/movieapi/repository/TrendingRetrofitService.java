package com.demo.movieapi.repository;

import com.demo.movieapi.model.TMDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrendingRetrofitService {
    @GET("trending/{media_type}/{time_window}")
    Call<TMDBResponse> getTrending(@Path("media_type") String mediaType, @Path("time_window") String time,
                                   @Query("api_key") String apiKey, @Query("page") int page);
}
