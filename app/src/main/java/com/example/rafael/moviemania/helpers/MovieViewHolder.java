package com.example.rafael.moviemania.helpers;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafael.moviemania.R;

/**
 * Created by Rafael on 7/6/2016.
 */
public class MovieViewHolder {

    private static final String LOG_TAG = MovieViewHolder.class.getSimpleName();

    ImageView imageView;
    TextView textView;

    MovieViewHolder(View v){
        imageView = (ImageView) v.findViewById(R.id.imageView);
        textView = (TextView) v.findViewById(R.id.textView);
    }
}
