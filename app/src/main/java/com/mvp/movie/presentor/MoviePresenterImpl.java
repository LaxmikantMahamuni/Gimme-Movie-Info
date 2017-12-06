package com.mvp.movie.presentor;

/**
 * Created by hardik on 01/11/17.
 */

import com.mvp.movie.util.Connectivity;
import com.mvp.movie.R;
import com.mvp.movie.adapter.model.MovieModel;
import com.mvp.movie.model.Result;
import com.mvp.movie.intercator.MovieInteractor;
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
    private Connectivity connectivity;

    //Now we need this presenter to speak to repo :: An Interactor
    public MoviePresenterImpl(MovieView param,Connectivity connectivity,MovieInteractor movieIntercator) {
        movieView = param;
        this.movieIntercator = movieIntercator;
        this.connectivity = connectivity;
    }

    /**
     * Accessed by View
     */
    public void onSubmitClicked() {
        String movieName = movieView.getMovieName();
        if (movieName.isEmpty()) {
            movieView.emptyMovieName(R.string.error_empty_movie_name);
            return;
        }
        if (!connectivity.isConnected()) {
            movieView.internetRequired(R.string.error_no_internet);
            return;
        }
        movieIntercator.getMovieInfo(movieName, this);
    }

    @Override
    public void onDestroy() {
        movieView = null;
    }

    @Override
    public void onMovieRetrieveError(Throwable e) {
        if (movieView != null) {
            movieView.onFailed(R.string.error_failed_to_get_movie_result);
        }
    }

    @Override
    public void onMovieRetrieveSuccess(Movie movie) {
        if (movie != null) {
            ArrayList<MovieModel> data = new ArrayList<>();
            List<Result> resultList = movie.getResults();
            for (Result var : resultList) {
                MovieModel var2 = new MovieModel(var.getId(), var.getTitle(), String.valueOf(var.getVoteAverage()), var.getReleaseDate(), var.getOverview(), var.getPosterPath());
                data.add(var2);
            }
            movieView.onSuccess(data);
        }
    }
}
