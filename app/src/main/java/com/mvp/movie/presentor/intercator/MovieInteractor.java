package com.mvp.movie.presentor.intercator;

/**
 * Created by hardik on 01/11/17.
 */

import com.mvp.movie.model.Movie;

/**
 * It allows presenter to speak to repo
 */
public interface MovieInteractor {

    /**
     * Acts as a Result callback of {@see MovieInteractorImpl#getMovieInfo}
     */
    interface OnMovieResultListener {
        void onMovieRetrieveError(Throwable e);

        void onMovieRetrieveSuccess(Movie movie);
    }

    void getMovieInfo(String movieName, OnMovieResultListener onMovieResultListener);
}
