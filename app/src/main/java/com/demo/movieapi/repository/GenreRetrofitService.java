package com.demo.movieapi.repository;

import com.demo.movieapi.model.GenreResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenreRetrofitService {
    @GET("genre/{media_type}/list")
    Call<GenreResponse> getGenre(@Path("media_type") String mediaType, @Query("api_key") String apiKey,
                                 @Query("language") String language);
}
