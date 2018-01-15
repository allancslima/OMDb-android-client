package com.allancslima.omdbclient.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.allancslima.omdbclient.Constants;
import com.allancslima.omdbclient.data.db.AppDatabase;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Allan Lima on 15/01/2018.
 */

public class DataFactory {

    public static <S> S provideService(Class<S> service, String BASE_URL) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(service);
    }

    public static AppDatabase provideDatabase(Context context) {
        AppDatabase database = Room
                .databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME)
                .build();

        return database;
    }
}