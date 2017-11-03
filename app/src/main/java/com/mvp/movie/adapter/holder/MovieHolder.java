package com.mvp.movie.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mvp.movie.R;

/**
 * Created by hardik on 02/11/17.
 */

public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView txtTitle;
    private TextView txtYear;
    private TextView txtRatings;
    private TextView txtDescription;
    public ImageView imageView;
    private OnViewClickedListener onViewClickedListener;

    public MovieHolder(View itemView, OnViewClickedListener onViewClickedListener) {
        super(itemView);
        txtTitle = itemView.findViewById(R.id.txt_title);
        txtYear = itemView.findViewById(R.id.txt_year);
        txtDescription = itemView.findViewById(R.id.txt_description);
        txtRatings = itemView.findViewById(R.id.txt_rate);
        imageView = itemView.findViewById(R.id.imageView);
        this.onViewClickedListener = onViewClickedListener;
    }

    public void setTitle(String txtTitle) {
        this.txtTitle.setText(txtTitle);
    }

    public void setYear(String txtYear) {
        this.txtYear.setText(txtYear);
    }

    public void setRatings(String txtRatings) {
        this.txtRatings.setText(txtRatings);
    }

    public void setDescription(String txtDescription) {
        this.txtDescription.setText(txtDescription);
    }

    @Override
    public void onClick(View v) {
        if (onViewClickedListener != null) {
            int position = getAdapterPosition();
            if (position != -1)
                onViewClickedListener.onItemClick(position,v, this);
        }
    }

    public interface OnViewClickedListener {
        void onItemClick(int position, View view, MovieHolder movieHolder);
    }
}
