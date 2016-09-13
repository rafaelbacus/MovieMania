package com.example.rafael.moviemania.helpers;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rafael.moviemania.R;
import com.example.rafael.moviemania.model.MovieModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Rafael on 7/6/2016.
 */
public class MovieListDownloader extends AsyncTask<Void, Void, String>{

    private static final String LOG_TAG = MovieListDownloader.class.getSimpleName();

    private Context context;
    private MovieGridAdapter adapter;
    private String QUERY_TYPE;

    private List<MovieModel> list;

    public MovieListDownloader(Context context, MovieGridAdapter adapter, String query_type){
        this.context = context;
        this.adapter = adapter;
        QUERY_TYPE = query_type;
    }

    @Override
    protected String doInBackground(Void... params) {
        String TMDB_MOVIE_URL = context.getString(R.string.TMDB_MOVIE_URL);
        final String API_KEY = "api_key";

        Uri uri = null;
        URL url = null;
        HttpURLConnection urlConnection = null;

        BufferedReader reader = null;
        String moviesJsonStr = null;

        try{
            //Get movie data;

            uri = Uri.parse(TMDB_MOVIE_URL).buildUpon()
                    .appendPath(QUERY_TYPE)
                    .appendQueryParameter(API_KEY, context.getString(R.string.TMDB_Key))
                    .build();

            url = new URL(uri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            if(inputStream == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuffer buffer = new StringBuffer();

            while((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }

            if(buffer.length() == 0){
                return null;
            }

            moviesJsonStr = buffer.toString();
        }
        catch(Exception e){
            Log.d(LOG_TAG,"Error getting movie data: " + e.toString());
            return null;
        }
        finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try{
                    reader.close();
                }
                catch(Exception e){
                    Log.d(LOG_TAG, "Error closing reader: " + e.toString());
                }
            }
        }

        return moviesJsonStr;
    }

    @Override
    protected void onPostExecute(String result) {
        try{
            MovieInfoParser parser = new MovieInfoParser(context);
            list = parser.Parse(result);

            MovieGridAdapter.setMovieList(list);
            adapter.notifyDataSetChanged();
        }
        catch(Exception e){
            Log.d(LOG_TAG, "Error while parsing movie info: " + e.toString());
        }
    }
}
