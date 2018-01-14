package com.allancslima.omdbclient.ui.main;

import android.arch.persistence.room.Room;
import android.util.Log;

import com.allancslima.omdbclient.Constants;
import com.allancslima.omdbclient.data.db.AppDatabase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Allan Lima on 14/01/2018.
 */

public class MainModel implements MainMVP.Model {

    private MainMVP.Presenter mPresenter;

    public MainModel(MainMVP.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getStoredMovies() {
        AppDatabase database = Room
                .databaseBuilder(mPresenter.getActivity(), AppDatabase.class, Constants.DATABASE_NAME)
                .build();

        database.getMovieDao().findAll()
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> mPresenter.getActivity()
                            .runOnUiThread( () -> mPresenter.onGotMovies(movies) ));
    }
}