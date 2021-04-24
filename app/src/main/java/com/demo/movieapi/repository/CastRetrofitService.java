package com.demo.movieapi.repository;

import com.demo.movieapi.model.CastCrew;
import com.demo.movieapi.model.MovieDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CastRetrofitService {
    @GET("movie/{movie_id}/credits")
    Call<CastCrew> getCastCrew(@Path("movie_id") int movieId, @Query("api_key") String apiKey,
                               @Query("language") String language);
}
