package com.allancslima.omdbclient.ui.addmovie;

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

    @Override
    public void addMovie(String title) {
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
    }

    @Override
    public void onError(String errorMsg) {
        mView.showToast(errorMsg);
    }
}