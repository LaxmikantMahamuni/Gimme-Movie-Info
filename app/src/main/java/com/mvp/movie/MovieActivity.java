package com.mvp.movie;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mvp.movie.adapter.MovieAdapter;
import com.mvp.movie.adapter.holder.MovieHolder;
import com.mvp.movie.adapter.model.MovieModel;
import com.mvp.movie.model.Abs;
import com.mvp.movie.model.AbsCon;
import com.mvp.movie.model.JustImmutableClass;
import com.mvp.movie.model.Movie;
import com.mvp.movie.model.MovieChild;
import com.mvp.movie.presentor.MoviePresenter;
import com.mvp.movie.presentor.MoviePresenterImpl;
import com.mvp.movie.service.BoundService;
import com.mvp.movie.thread.ThreadRunnable;
import com.mvp.movie.view.MovieView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

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
        moviePresenter = new MoviePresenterImpl(this,new ConnectionManager());
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
    public void onFailed(String errorMessage) {
        Toast.makeText(context, "Failed, Reason " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(int position, MovieModel movieModel, MovieHolder movieHolder) {
        //TODO Show detail screen
    }
}
