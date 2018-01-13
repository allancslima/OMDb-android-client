package com.allancslima.omdbclient.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.allancslima.omdbclient.data.db.dao.MovieDao;
import com.allancslima.omdbclient.data.db.model.Movie;

/**
 * Created by Allan Lima on 12/01/2018.
 */

@Database(entities = {Movie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();
}