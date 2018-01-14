package com.allancslima.omdbclient.ui.newmovie;

import android.support.v7.app.AppCompatActivity;

import com.allancslima.omdbclient.data.db.model.Movie;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public interface NewMovieMVP {

    interface View {
        public void showToast(String msg);
        public void setEnabledAddButton(boolean enabled);
        public void setEmptyTitleInputError();
        public void close();
    }

    interface Presenter {
        public AppCompatActivity getActivity();
        public void addMovie(String title);
        public void onInsertedMovie();
        public void onEmptyTitleInputError();
        public void onError(String errorMsg);
    }

    interface Model {
        public void insertMovie(Movie movie);
    }
}