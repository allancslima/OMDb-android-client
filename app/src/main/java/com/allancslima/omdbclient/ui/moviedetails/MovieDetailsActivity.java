package com.allancslima.omdbclient.ui.moviedetails;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.allancslima.omdbclient.R;
import com.allancslima.omdbclient.data.db.model.Movie;
import com.allancslima.omdbclient.databinding.ActivityMovieDetailsBinding;
import com.allancslima.omdbclient.utils.ImageUtils;

/**
 * Created by Allan Lima on 14/01/2018.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovieDetailsBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_movie_details);

        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        binding.setMovie(movie);

        Bitmap poster = ImageUtils.loadImageFromStorage(this, movie.getId());
        binding.imagePoster.setImageBitmap(poster);
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