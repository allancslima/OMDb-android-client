package com.allancslima.omdbclient.ui.addmovie;

import android.arch.persistence.room.Room;

import com.allancslima.omdbclient.data.db.AppDatabase;
import com.allancslima.omdbclient.data.db.model.Movie;
import com.allancslima.omdbclient.data.network.OMDbService;
import com.allancslima.omdbclient.data.network.RetrofitFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public class AddMovieModel implements AddMovieMVP.Model {

    private AddMovieMVP.Presenter mPresenter;

    public AddMovieModel(AddMovieMVP.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void insertMovie(Movie movie) {
        if ( movie.getTitle().trim().isEmpty() )
            mPresenter.onEmptyTitleInputError();
        else
            requestMovie(movie.getTitle());
    }

    private void requestMovie(String title) {
        OMDbService service = RetrofitFactory.provideService(
                OMDbService.class, OMDbService.BASE_URL);

        service.getMovie(title)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    if (movieResponse.isResponse()) {
                        storeMovie(movieResponse.getMovie());
                    } else {
                        mPresenter.onError("Movie not found!");
                    }
                }, throwable -> mPresenter.onError("You are connected?"));
    }

    private void storeMovie(Movie movie) {
        AppDatabase db = Room
                .inMemoryDatabaseBuilder(mPresenter.getContext(), AppDatabase.class)
                .build();
    }
}