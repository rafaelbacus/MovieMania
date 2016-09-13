package com.example.rafael.moviemania.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.rafael.moviemania.R;
import com.example.rafael.moviemania.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 7/6/2016.
 */
public class MovieGridAdapter extends BaseAdapter {

    private static final String LOG_TAG = MovieGridAdapter.class.getSimpleName();

    Context context;
    private static List<MovieModel> movieList;

    public MovieGridAdapter(Context c){
        context = c;

        movieList = new ArrayList<MovieModel>();
    }

    public List<MovieModel> getMovieList() {
        return movieList;
    }

    public static void setMovieList(List<MovieModel> list) {
        MovieGridAdapter.movieList = list;
    }


    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View movieView = convertView;
        MovieViewHolder holder = null;

        if(movieView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            movieView = (View) inflater.inflate(R.layout.layout_grid_item, parent, false);
            holder = new MovieViewHolder(movieView);
            movieView.setTag(holder);
        } else {
            holder = (MovieViewHolder) movieView.getTag();
        }

        MovieModel temp = movieList.get(position);

        Picasso.with(context).load(temp.getPoster()).into(holder.imageView);
        holder.textView.setText(temp.getTitle());

        return movieView;
    }
}