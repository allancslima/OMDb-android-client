package com.allancslima.omdbclient.data.network;

import com.allancslima.omdbclient.data.network.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public interface OMDbService {

    String BASE_URL = "http://www.omdbapi.com";

    @GET("/?apiKey=9ffa6686")
    Call<MovieResponse> getMovie(@Query("t") String title);
}