package com.mvp.movie.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mvp.movie.util.ConnectionManager;
import com.mvp.movie.R;
import com.mvp.movie.adapter.MovieAdapter;
import com.mvp.movie.adapter.holder.MovieHolder;
import com.mvp.movie.adapter.model.MovieModel;
import com.mvp.movie.presentor.MoviePresenter;
import com.mvp.movie.presentor.MoviePresenterImpl;
import com.mvp.movie.intercator.MovieInteractorImpl;
import com.mvp.movie.view.MovieView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hardik on 01/11/17.
 */

/**
 * View of MVP
 * Shows input for movie name
 */
public class MovieActivity extends Activity implements View.OnClickListener, MovieView, MovieAdapter.OnAdapterViewClickedListener {


    private MoviePresenter moviePresenter;
    private EditText edtMovieName;
    private Context context;
    private RecyclerView recyclerViewMovies;
    private LinearLayoutManager linearLayoutManager;
    private MovieAdapter movieAdapter;
    private ArrayList<MovieModel> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        context = getApplicationContext();
        setupViews();
        moviePresenter = new MoviePresenterImpl(this, new ConnectionManager(), new MovieInteractorImpl());
    }

    private void setupViews() {
        findViewById(R.id.btnSearch).setOnClickListener(this);

        edtMovieName = findViewById(R.id.edtMovie);
        recyclerViewMovies = findViewById(R.id.recycler_movies);

        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMovies.setLayoutManager(linearLayoutManager);

        data = new ArrayList<>();
        movieAdapter = new MovieAdapter(context, this);
        movieAdapter.setData(data);
        recyclerViewMovies.setAdapter(movieAdapter);
    }

    @Override
    public void onClick(View v) {
        moviePresenter.onSubmitClicked();
    }

    @Override
    protected void onDestroy() {
        moviePresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void emptyMovieName(int resId) {
        Toast.makeText(context, getString(resId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void internetRequired(int resId) {
        Toast.makeText(context, getString(resId), Toast.LENGTH_LONG).show();
    }

    @Override
    public String getMovieName() {
        return edtMovieName.getText().toString();
    }

    @Override
    public void onSuccess(List<MovieModel> data) {
        movieAdapter.setData(new ArrayList<>(data));
    }

    @Override
    public void onFailed(int resId) {
        Toast.makeText(context, getString(resId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(int position, MovieModel movieModel, MovieHolder movieHolder) {
        //TODO Show detail screen
    }
}
