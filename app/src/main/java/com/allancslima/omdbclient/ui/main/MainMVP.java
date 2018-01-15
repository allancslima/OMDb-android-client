package com.allancslima.omdbclient.ui.main;

import android.support.v7.app.AppCompatActivity;

import com.allancslima.omdbclient.data.db.model.Movie;

import java.util.List;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public interface MainMVP {

    interface View {
        public void setAdapterDataset(List<Movie> dataset);
    }

    interface Presenter {
        public AppCompatActivity getActivity();
        public void loadMovies();
        public void onGotMovies(List<Movie> movies);
    }

    interface Model {
        public void getStoredMovies();
    }
}