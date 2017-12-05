package com.mvp.movie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hardik on 01/11/17.
 */

public class MovieChild { //extends Movie {

    public int getMovieChild() {
        return movieChild;
    }

    public void setMovieChild(int movieChild) {
        this.movieChild = movieChild;
    }

    public int movieChild = 3;

    private void iamprivate(){

    }

    public static void iamstatic(){

    }
}