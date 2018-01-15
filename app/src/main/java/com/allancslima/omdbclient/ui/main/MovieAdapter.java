package com.allancslima.omdbclient.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allancslima.omdbclient.R;
import com.allancslima.omdbclient.base.BaseAdapter;
import com.allancslima.omdbclient.data.db.model.Movie;
import com.allancslima.omdbclient.ui.moviedetails.MovieDetailsActivity;
import com.allancslima.omdbclient.utils.ImageUtils;

import java.util.List;

/**
 * Created by Allan Lima on 13/01/2018.
 */

public class MovieAdapter extends BaseAdapter {

    public MovieAdapter(Context context, List<Movie> dataset) {
        super(context, dataset);
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        MovieViewHolder holder = new MovieViewHolder(view);

        view.setOnClickListener((View v) -> {
            Intent intent = new Intent(mContext, MovieDetailsActivity.class);
            Movie movie = (Movie) getItem(holder.getAdapterPosition());

            intent.putExtra("movie", movie);
            mContext.startActivity(intent);
        });

        return holder;
    }

    @Override
    protected void onBindData(RecyclerView.ViewHolder holder, Object object) {
        Movie movie = (Movie) object;
        MovieViewHolder movieHolder = (MovieViewHolder) holder;
        Bitmap poster = ImageUtils.loadImageFromStorage(mContext, movie.getId());

        movieHolder.imagePoster.setImageBitmap(poster);
        movieHolder.textTitle.setText(movie.getTitle());
        movieHolder.textYear.setText(movie.getYear());
    }

    protected class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagePoster;
        public TextView textTitle;
        public TextView textYear;

        public MovieViewHolder(View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.image_poster);
            textTitle = itemView.findViewById(R.id.text_title);
            textYear = itemView.findViewById(R.id.text_year);
        }
    }
}