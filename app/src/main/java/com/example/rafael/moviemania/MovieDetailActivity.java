package com.example.rafael.moviemania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setTitle(getString(R.string.title_activity_movie_detail));

        Intent intent = getIntent();
        TextView title = (TextView) findViewById(R.id.movie_detail_title);
        TextView synopsis = (TextView) findViewById(R.id.movie_detail_synopsis);
        TextView rating = (TextView) findViewById(R.id.movie_detail_rating);
        TextView release_date = (TextView) findViewById(R.id.movie_detail_release_date);

        title.setText(intent.getStringExtra(getString(R.string.Model_Movie_Title)));
        synopsis.setText("Synopsis:\n" + intent.getStringExtra(getString(R.string.Model_Movie_Synopsis)));
        rating.setText("Rating: " + intent.getStringExtra(getString(R.string.Model_Movie_Rating)));
        release_date.setText("Release date: " + intent.getStringExtra(getString(R.string.Model_Movie_Release_Date)));
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}

