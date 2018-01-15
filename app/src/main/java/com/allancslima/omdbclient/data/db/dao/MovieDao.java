package com.allancslima.omdbclient.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.allancslima.omdbclient.data.db.model.Movie;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Allan Lima on 12/01/2018.
 */

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies")
    Flowable<List<Movie>> findAll();

    @Insert
    long insert(Movie movie);

    @Delete
    void delete(Movie movie);
}