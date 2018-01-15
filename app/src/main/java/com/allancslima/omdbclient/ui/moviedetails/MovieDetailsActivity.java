package com.allancslima.omdbclient.ui.moviedetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.allancslima.omdbclient.R;
import com.allancslima.omdbclient.data.db.model.Movie;

import java.util.Locale;

/**
 * Created by Allan Lima on 14/01/2018.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        TextView textTitle = findViewById(R.id.text_title);
        TextView textYear = findViewById(R.id.text_year);
        TextView textGenre = findViewById(R.id.text_genre);
        TextView textActors = findViewById(R.id.text_actors);
        TextView textImdbRating = findViewById(R.id.text_imdb_rating);
        TextView textImdbVotes = findViewById(R.id.text_imdb_votes);
        TextView textProduction = findViewById(R.id.text_production);

        textTitle.setText(movie.getTitle());
        textYear.setText(movie.getYear());
        textGenre.setText(movie.getGenre());
        textActors.setText(movie.getActors());
        textImdbRating.setText(movie.getImdbRating());
        textImdbVotes.setText(movie.getImdbVotes());
        textProduction.setText(movie.getProduction());

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}