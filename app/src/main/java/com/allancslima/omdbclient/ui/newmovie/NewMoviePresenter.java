package com.allancslima.omdbclient.ui.newmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.allancslima.omdbclient.R;
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

    public AppCompatActivity getActivity() {
        return (AppCompatActivity) mView;
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
        getActivity().setResult(AppCompatActivity.RESULT_OK, new Intent());
        mView.showToast(getActivity()
                .getResources()
                .getString(R.string.msg_inserted_movie));
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