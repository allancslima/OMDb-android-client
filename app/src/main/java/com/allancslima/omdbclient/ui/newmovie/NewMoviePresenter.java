package com.allancslima.omdbclient.ui.newmovie;

import android.content.Context;

import com.allancslima.omdbclient.data.db.model.Movie;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public class NewMoviePresenter implements NewMovieMVP.Presenter {

    private NewMovieMVP.View mView;
    private NewMovieMVP.Model mModel;

    public NewMoviePresenter(NewMovieMVP.View view) {
        mView = view;
        mModel = new NewMovieModel(this);
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
        mView.showToast("O filme foi inserido com sucesso!");
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