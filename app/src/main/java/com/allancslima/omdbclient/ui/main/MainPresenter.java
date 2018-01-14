package com.allancslima.omdbclient.ui.main;

import android.support.v7.app.AppCompatActivity;

import com.allancslima.omdbclient.data.db.model.Movie;

import java.util.List;

/**
 * Created by Allan Lima on 14/01/2018.
 */

public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View mView;
    private MainMVP.Model mModel;

    public MainPresenter(MainMVP.View view) {
        mView = view;
        mModel = new MainModel(this);
    }

    @Override
    public AppCompatActivity getActivity() {
        return (AppCompatActivity) mView;
    }

    @Override
    public void loadMovies() {
        mModel.getStoredMovies();
    }

    @Override
    public void onGotMovies(List<Movie> movies) {
        mView.setAdapter(movies);
    }
}