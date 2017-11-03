package com.mvp.movie.presentor;

/**
 * Created by hardik on 01/11/17.
 */

public interface MoviePresenter {
    void onSubmitClicked(String movieName);

    void onDestroy();
}
