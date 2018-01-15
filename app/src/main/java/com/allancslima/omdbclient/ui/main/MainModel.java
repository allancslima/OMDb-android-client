package com.allancslima.omdbclient.ui.main;

import com.allancslima.omdbclient.data.DataFactory;

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
        DataFactory.provideDatabase(mPresenter.getActivity())
                .getMovieDao().findAll()
                .observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> mPresenter.getActivity()
                            .runOnUiThread( () -> mPresenter.onGotMovies(movies) ));
    }
}