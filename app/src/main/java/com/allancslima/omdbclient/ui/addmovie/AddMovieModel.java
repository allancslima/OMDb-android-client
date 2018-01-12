package com.allancslima.omdbclient.ui.addmovie;

import android.util.Log;

import com.allancslima.omdbclient.data.db.model.Movie;
import com.allancslima.omdbclient.data.network.OMDbService;
import com.allancslima.omdbclient.data.network.RetrofitFactory;
import com.allancslima.omdbclient.data.network.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public class AddMovieModel implements AddMovieMVP.Model {
    private final String TAG = getClass().getSimpleName();

    private AddMovieMVP.Presenter mPresenter;

    public AddMovieModel(AddMovieMVP.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void insertMovie(Movie movie) {
        if ( movie.getTitle().trim().isEmpty() )
            mPresenter.onEmptyTitleInputError();
        else
            saveMovie(movie);
    }

    private void saveMovie(Movie movie) {
        OMDbService service = RetrofitFactory
                .provideService(OMDbService.class, OMDbService.BASE_URL);

        Call<MovieResponse> call = service.getMovie(movie.getTitle());
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body().isResponse()) {
                    Log.d(TAG, "Title: " + response.body().getTitle());
                } else {
                    mPresenter.onError("Movie not found!");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mPresenter.onError("Error! You are connected?");
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }
}