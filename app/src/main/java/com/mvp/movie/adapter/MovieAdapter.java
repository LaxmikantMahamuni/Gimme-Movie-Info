package com.mvp.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mvp.movie.R;
import com.mvp.movie.adapter.holder.MovieHolder;
import com.mvp.movie.adapter.model.MovieModel;

import java.util.ArrayList;

/**
 * Created by hardik on 02/11/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {
    private ArrayList<MovieModel> data;
    private Context context;
    private final String imageUrlPrefix = "https://image.tmdb.org/t/p/w300/";
    private OnAdapterViewClickedListener onAdapterViewClickedListener;

    public MovieAdapter(Context context,OnAdapterViewClickedListener onAdapterViewClickedListener) {
        this.context = context;
        this.onAdapterViewClickedListener = onAdapterViewClickedListener;
        data = new ArrayList<>();
    }

    public void setData(ArrayList<MovieModel> _data) {
        if (data != null) {
            data.clear();
        }
        data = _data;
        notifyDataSetChanged();
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_movie, parent, false);
        return new MovieHolder(view,onViewClickedListener);
    }

    private MovieHolder.OnViewClickedListener onViewClickedListener = new MovieHolder.OnViewClickedListener() {
        @Override
        public void onItemClick(int position,View view, MovieHolder movieHolder) {
            MovieModel movieModel = data.get(position);
            onAdapterViewClickedListener.onItemClicked(position,movieModel,movieHolder);
        }
    };

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        MovieModel movieModel = data.get(position);
        if (movieModel != null) {
            String movieName = movieModel.getMovieName();
            String description = movieModel.getMovieDescription();
            String ratings = movieModel.getMovieRatings();
            String releaseYear = movieModel.getMovieReleaseYear();
            String posterPath = movieModel.getPosterPath();

            holder.setTitle(movieName);
            holder.setDescription(description);
            holder.setRatings(ratings);
            holder.setYear(releaseYear);
            if(posterPath != null) {
                String imagePath = imageUrlPrefix.concat(posterPath);
                Glide.with(context).
                        load(imagePath).
                        into(holder.imageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnAdapterViewClickedListener{
        void onItemClicked(int position,MovieModel movieModel,MovieHolder movieHolder);
    }

}
