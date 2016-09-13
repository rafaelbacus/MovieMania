package com.example.rafael.moviemania;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.rafael.moviemania.helpers.MovieGridAdapter;
import com.example.rafael.moviemania.helpers.MovieListDownloader;
import com.example.rafael.moviemania.model.MovieModel;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private GridView gridView;
    private MovieGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.title_activity_main));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                startActivity(new Intent(this,SettingsActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        boolean isNetworkConnected = checkNetworkConnectivity();

        if(isNetworkConnected){
            setContentView(R.layout.activity_main);

            gridView = (GridView) findViewById(R.id.gridview);
            adapter = new MovieGridAdapter(getApplicationContext());
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MovieModel model = (MovieModel) parent.getAdapter().getItem(position);

                    Intent intent = new Intent(parent.getContext(), MovieDetailActivity.class);

                    intent.putExtra(getString(R.string.Model_Movie_Title), model.getTitle());
                    intent.putExtra(getString(R.string.Model_Movie_Synopsis),model.getSynopsis());
                    intent.putExtra(getString(R.string.Model_Movie_Rating),model.getRating());
                    intent.putExtra(getString(R.string.Model_Movie_Release_Date),model.getReleaseDate());

                    startActivity(intent);
                }
            });

            updateGridView();
        }
        else {
            setContentView(R.layout.layout_message);
            setMessageView(getString(R.string.connectivity_none));
        }
    }

    public void updateGridView(){
        //Get sharedpref for sortby
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String pref_sort = sharedPreferences.getString(
                getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_default)
        );

        new MovieListDownloader(this, adapter, pref_sort).execute();
    }

    public void setMessageView(String msg){
        TextView messageView = (TextView) findViewById(R.id.messageview);
        messageView.setText(msg);
    }


    public boolean checkNetworkConnectivity(){
        boolean isWifiConn;
        boolean isMobileConn;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        isWifiConn = networkInfo.isConnected();

        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        isMobileConn = networkInfo.isConnected();

        return isWifiConn || isMobileConn;
    }
}
