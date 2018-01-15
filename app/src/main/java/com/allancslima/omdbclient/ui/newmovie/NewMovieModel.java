package com.allancslima.omdbclient.ui.newmovie;

import android.graphics.Bitmap;

import com.allancslima.omdbclient.R;
import com.allancslima.omdbclient.data.DataFactory;
import com.allancslima.omdbclient.data.db.AppDatabase;
import com.allancslima.omdbclient.data.db.model.Movie;
import com.allancslima.omdbclient.data.network.OMDbService;
import com.allancslima.omdbclient.utils.ImageUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public class NewMovieModel implements NewMovieMVP.Model {

    private NewMovieMVP.Presenter mPresenter;

    public NewMovieModel(NewMovieMVP.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void insertMovie(Movie movie) {
        if (movie.getTitle().trim().isEmpty())
            mPresenter.onEmptyTitleInputError();
        else
            requestMovie(movie.getTitle());
    }

    private void requestMovie(String title) {
        OMDbService service = DataFactory.provideService(
                OMDbService.class, OMDbService.BASE_URL);

        service.getMovie(title)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    if (movieResponse.isResponse())
                        storeMovie(movieResponse.getMovie());
                    else
                        mPresenter.onError(mPresenter.getActivity()
                                .getResources()
                                .getString(R.string.msg_movie_not_found));
                }, throwable -> mPresenter.onError(mPresenter.getActivity()
                        .getResources()
                        .getString(R.string.msg_movie_request_error)));
    }

    private void storeMovie(Movie movie) {
        new Thread(() -> {
            final long insertedMovieId = DataFactory
                    .provideDatabase(mPresenter.getActivity())
                    .getMovieDao()
                    .insert(movie);

            saveMoviePoster(movie.getPosterUrl(), insertedMovieId);

            mPresenter.getActivity()
                    .runOnUiThread(() -> mPresenter.onInsertedMovie());
        }).start();
    }

    private void saveMoviePoster(String posterUrl, long objectId) {
        try {
            Bitmap poster = Picasso.with(mPresenter.getActivity())
                    .load(posterUrl)
                    .get();

            ImageUtils.saveToInternalStorage(mPresenter.getActivity(), poster, objectId);
        } catch (IOException e) {
            e.printStackTrace();
            mPresenter.onError(mPresenter.getActivity()
                    .getResources()
                    .getString(R.string.msg_poster_rescue_error));
        }
    }
}