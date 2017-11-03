package com.mvp.movie.api;

import com.mvp.movie.model.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hardik on 01/11/17.
 */

public interface APIService {
    @GET("/3/search/movie")
    Observable<Movie> searchMovie(@Query("api_key") String apiKey, @Query("query") String query);
}