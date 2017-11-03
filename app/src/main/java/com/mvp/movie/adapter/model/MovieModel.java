package com.mvp.movie.adapter.model;

/**
 * Created by hardik on 02/11/17.
 */

public class MovieModel {
    int id;
    String movieName;
    String movieRatings;
    String movieReleaseYear;
    String movieDescription;


    String imagePath;

    public MovieModel(int id , String movieName, String movieRatings, String movieReleaseYear, String movieDescription,String imagePath) {
        this.id = id;
        this.movieName = movieName;
        this.movieRatings = movieRatings;
        this.movieReleaseYear = movieReleaseYear;
        this.movieDescription = movieDescription;
        this.imagePath = imagePath;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings(String movieRatings) {
        this.movieRatings = movieRatings;
    }

    public String getMovieReleaseYear() {
        return movieReleaseYear;
    }

    public void setMovieReleaseYear(String movieReleaseYear) {
        this.movieReleaseYear = movieReleaseYear;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getPosterPath() {
        return imagePath;
    }

}
