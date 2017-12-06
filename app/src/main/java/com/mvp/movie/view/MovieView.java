package com.mvp.movie.view;


import com.mvp.movie.adapter.model.MovieModel;

import java.util.List;

/**
 * Created by hardik on 01/11/17.
 */

public interface MovieView {

    void emptyMovieName(int resId);

    void internetRequired(int resId);

    String getMovieName();

    void onSuccess(List<MovieModel> movieModel);

    void onFailed(int resId);
}
