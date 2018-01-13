package com.allancslima.omdbclient.ui.addmovie;

import android.content.Context;

import com.allancslima.omdbclient.data.db.model.Movie;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public class AddMoviePresenter implements AddMovieMVP.Presenter {

    private AddMovieMVP.View mView;
    private AddMovieMVP.Model mModel;

    public AddMoviePresenter(AddMovieMVP.View view) {
        mView = view;
        mModel = new AddMovieModel(this);
    }

    public Context getContext() {
        return (Context) mView;
    }

    @Override
    public void addMovie(String title) {
        mView.setEnabledAddButton(false);

        Movie movie = new Movie();
        movie.setTitle(title);
        mModel.insertMovie(movie);
    }

    @Override
    public void onInsertedMovie() {
        mView.showToast("The movie was successfully inserted!");
        mView.close();
    }

    @Override
    public void onEmptyTitleInputError() {
        mView.setEmptyTitleInputError();
        mView.setEnabledAddButton(true);
    }

    @Override
    public void onError(String errorMsg) {
        mView.showToast(errorMsg);
        mView.setEnabledAddButton(true);
    }
}