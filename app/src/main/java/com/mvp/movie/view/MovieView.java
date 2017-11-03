package com.mvp.movie.view;


import com.mvp.movie.adapter.model.MovieModel;

import java.util.List;

/**
 * Created by hardik on 01/11/17.
 */

public interface MovieView {
    void onSuccess(List<MovieModel> movieModel);

    void onFailed(String errorMessage);
}
