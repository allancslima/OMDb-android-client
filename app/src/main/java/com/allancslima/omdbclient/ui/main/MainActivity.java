package com.allancslima.omdbclient.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.allancslima.omdbclient.R;
import com.allancslima.omdbclient.data.db.model.Movie;
import com.allancslima.omdbclient.ui.addmovie.AddMovieActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        MovieAdapter adapter = new MovieAdapter(this, getMovies());
        mRecyclerView.setAdapter(adapter);

        FloatingActionButton btnAddMovie = findViewById(R.id.button_add_movie);
        btnAddMovie.setOnClickListener((View v) ->
                startActivity(new Intent(MainActivity.this, AddMovieActivity.class)));
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Movie m = new Movie();
            m.setTitle("Justice League");
            m.setYear("2017");
            m.setGenre("Action");
            movies.add(m);
        }
        return movies;
    }
}