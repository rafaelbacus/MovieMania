package com.example.rafael.moviemania.helpers;

import android.content.Context;
import android.util.Log;

import com.example.rafael.moviemania.R;
import com.example.rafael.moviemania.model.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 7/6/2016.
 */
public class MovieInfoParser {

    private static final String LOG_TAG = MovieInfoParser.class.getSimpleName();

    private Context context;

    public MovieInfoParser(Context c){
        context = c;
    }

    private static final String TITLE = "original_title";
    private static final String POSTER = "poster_path";
    private static final String SYNOPSIS = "overview";
    private static final String RATING = "vote_average";
    private static final String RELEASE_DATE = "release_date";

    public List<MovieModel> Parse(String moviesJsonStr) throws JSONException {
        JSONObject jsonObject = new JSONObject(moviesJsonStr);
        JSONArray movies = jsonObject.getJSONArray("results");
        JSONObject movie;

        final String TMDB_IMAGE_URL = context.getString(R.string.TMDB_IMAGE_URL);
        final String IMAGE_SIZE_PREF = "w500/";

        List<MovieModel> list = new ArrayList<MovieModel>();

        for(int i = 0; i < movies.length(); i++){
            movie = movies.getJSONObject(i);
            MovieModel model = new MovieModel();

            try{
                model.setTitle(movie.getString(TITLE));
                model.setPoster(TMDB_IMAGE_URL + IMAGE_SIZE_PREF + movie.getString(POSTER));
                model.setSynopsis(movie.getString(SYNOPSIS));
                model.setRating(movie.getString(RATING));
                model.setReleaseDate(movie.getString(RELEASE_DATE));
            }
            catch(Exception e) {
                Log.d(LOG_TAG, "Error while parsing movie info: " + e.toString());
            }

            list.add(model);
        }

        return list;
    }
}
