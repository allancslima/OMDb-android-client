package com.allancslima.omdbclient.ui.addmovie;

import com.allancslima.omdbclient.data.db.model.Movie;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public interface AddMovieMVP {

    interface View {
        public void showToast(String msg);
        public void setEmptyTitleInputError();
        public void close();
    }

    interface Presenter {
        public void addMovie(String title);
        public void onInsertedMovie();
        public void onEmptyTitleInputError();
        public void onError(String errorMsg);
    }

    interface Model {
        public void insertMovie(Movie movie);
    }
}