package com.allancslima.omdbclient.ui.newmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.allancslima.omdbclient.R;
import com.allancslima.omdbclient.data.db.model.Movie;

import java.lang.ref.WeakReference;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public class NewMoviePresenter implements NewMovieMVP.Presenter {

    private WeakReference<NewMovieMVP.View> mView;
    private NewMovieMVP.Model mModel;

    public NewMoviePresenter(NewMovieMVP.View view) {
        mView = new WeakReference<>(view);
        mModel = new NewMovieModel(this);
    }

    public AppCompatActivity getActivity() {
        return (AppCompatActivity) mView.get();
    }

    @Override
    public void addMovie(String title) {
        mView.get().setEnabledAddButton(false);

        Movie movie = new Movie();
        movie.setTitle(title);
        mModel.insertMovie(movie);
    }

    @Override
    public void onInsertedMovie() {
        getActivity().setResult(AppCompatActivity.RESULT_OK, new Intent());
        mView.get().showToast(getActivity()
                .getResources()
                .getString(R.string.msg_inserted_movie));
        mView.get().close();
    }

    @Override
    public void onEmptyTitleInputError() {
        mView.get().setEmptyTitleInputError();
        mView.get().setEnabledAddButton(true);
    }

    @Override
    public void onError(String errorMsg) {
        mView.get().showToast(errorMsg);
        mView.get().setEnabledAddButton(true);
    }
}