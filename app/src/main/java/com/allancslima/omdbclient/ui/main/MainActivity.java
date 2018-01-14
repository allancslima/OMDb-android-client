package com.allancslima.omdbclient.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.allancslima.omdbclient.Constants;
import com.allancslima.omdbclient.R;
import com.allancslima.omdbclient.data.db.model.Movie;
import com.allancslima.omdbclient.ui.newmovie.NewMovieActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMVP.View {

    private RecyclerView mRecyclerView;
    private MainMVP.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        FloatingActionButton btnAddMovie = findViewById(R.id.button_add_movie);
        btnAddMovie.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, NewMovieActivity.class);
            startActivityForResult(intent, Constants.NEW_MOVIE_REQUEST_CODE);
        });

        mPresenter.loadMovies();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.NEW_MOVIE_REQUEST_CODE) {
            if (resultCode == RESULT_OK)
                mPresenter.loadMovies();
        }
    }

    @Override
    public void setAdapter(List<Movie> dataset) {
        MovieAdapter adapter = new MovieAdapter(this, dataset);
        mRecyclerView.setAdapter(adapter);
    }
}