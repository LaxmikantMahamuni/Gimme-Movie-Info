package com.mvp.movie.presentor;

/**
 * Created by hardik on 01/11/17.
 */

import com.mvp.movie.adapter.model.MovieModel;
import com.mvp.movie.model.Result;
import com.mvp.movie.presentor.intercator.MovieInteractor;
import com.mvp.movie.presentor.intercator.MovieInteractorImpl;
import com.mvp.movie.view.MovieView;
import com.mvp.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * A Presenter that implements MoviePresenter and OnMovieResultListener
 * MoviePresenter is a bridge between view and presenter -
 * The view is not aware about the communication between presenter and model(repo :: data source)
 * <p>
 * MovieInteractor.OnMovieResultListener is a bridge between model and presenter -
 */
public class MoviePresenterImpl implements MoviePresenter, MovieInteractor.OnMovieResultListener {
    private MovieView movieView;
    private MovieInteractor movieIntercator;

    //Now we need this presenter to speak to repo :: An Interactor
    public MoviePresenterImpl(MovieView param) {
        movieView = param;
        movieIntercator = new MovieInteractorImpl();
    }

    /**
     * Accessed by View
     *
     * @param movieName movie name as query
     */
    public void onSubmitClicked(String movieName) {
        movieIntercator.getMovieInfo(movieName, this);
    }

    @Override
    public void onDestroy() {
        movieView = null;
    }

    @Override
    public void onMovieRetrieveError(Throwable e) {
        if (movieView != null) {
            movieView.onFailed(e.getMessage());
        }
    }

    @Override
    public void onMovieRetrieveSuccess(Movie movie) {
        if (movie != null) {
            ArrayList<MovieModel> data = new ArrayList<>();
            List<Result> resultList = movie.getResults();
            for (Result var : resultList) {
                MovieModel var2 = new MovieModel(var.getId(),var.getTitle(), String.valueOf(var.getVoteAverage()), var.getReleaseDate(), var.getOverview(),var.getPosterPath());
                data.add(var2);
            }
            movieView.onSuccess(data);
        }
    }
}
