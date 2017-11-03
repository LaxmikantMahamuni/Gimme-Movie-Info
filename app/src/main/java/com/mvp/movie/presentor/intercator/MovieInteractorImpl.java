package com.mvp.movie.presentor.intercator;

import com.mvp.movie.api.RestClient;
import com.mvp.movie.model.Movie;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hardik on 01/11/17.
 */

public class MovieInteractorImpl implements MovieInteractor {
    String api_key = "7c67790b259a965357bdf4b03febee52";

    @Override
    public void getMovieInfo(String movieName, final OnMovieResultListener onMovieResultListener) {
        RestClient.getRestClient().searchMovie(api_key, movieName).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Movie movie) {
                        onMovieResultListener.onMovieRetrieveSuccess(movie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onMovieResultListener.onMovieRetrieveError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
