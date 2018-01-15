package com.allancslima.omdbclient.ui.newmovie;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.allancslima.omdbclient.R;
import com.allancslima.omdbclient.databinding.ActivityNewMovieBinding;

/**
 * Created by Allan Lima on 11/01/2018.
 */

public class NewMovieActivity extends AppCompatActivity implements NewMovieMVP.View {

    private TextInputLayout editTitleWrapper;
    private TextInputEditText editTitle;
    private AppCompatButton buttonAdd;
    private NewMovieMVP.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNewMovieBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_new_movie);

        mPresenter = new NewMoviePresenter(this);

        setTitle(R.string.title_add_movie);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTitleWrapper = binding.editTitleWrapper;
        editTitle = binding.editTitle;

        buttonAdd = binding.buttonAdd;
        buttonAdd.setOnClickListener((View v) -> mPresenter
                .addMovie( editTitle.getText().toString() ));
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

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEnabledAddButton(boolean enabled) {
        buttonAdd.setEnabled(enabled);
    }

    @Override
    public void setEmptyTitleInputError() {
        editTitleWrapper.setErrorEnabled(true);
        editTitleWrapper.setError(getResources().getString(R.string.msg_empty_input_error));
    }

    @Override
    public void close() {
        finish();
    }
}