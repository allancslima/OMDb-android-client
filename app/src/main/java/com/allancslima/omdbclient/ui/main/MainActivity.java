package com.allancslima.omdbclient.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.allancslima.omdbclient.Constants;
import com.allancslima.omdbclient.R;
import com.allancslima.omdbclient.data.db.model.Movie;
import com.allancslima.omdbclient.databinding.ActivityMainBinding;
import com.allancslima.omdbclient.ui.newmovie.NewMovieActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMVP.View {

    private RecyclerView mRecyclerView;
    private TextView textNoMovies;
    private MovieAdapter mAdapter;
    private MainMVP.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);

        mPresenter = new MainPresenter(this);

        textNoMovies = binding.textNoMovies;
        mRecyclerView = binding.recyclerView;
        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        FloatingActionButton btnAddMovie = binding.buttonAddMovie;
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
    public void setAdapterDataset(List<Movie> dataset) {
        mAdapter = new MovieAdapter(this, dataset);
        mRecyclerView.setAdapter(mAdapter);

        textNoMovies.setVisibility(dataset.size() == 0 ? View.VISIBLE : View.GONE);
    }
}