package com.allancslima.omdbclient.ui.newmovie;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.allancslima.omdbclient.R;

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
        setContentView(R.layout.activity_add_movie);
        setTitle(R.string.title_add_movie);

        mPresenter = new NewMoviePresenter(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTitleWrapper = findViewById(R.id.edit_title_wrapper);
        editTitle = findViewById(R.id.edit_title);

        buttonAdd = findViewById(R.id.button_add);
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