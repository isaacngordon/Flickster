package com.isaacngordon.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.isaacngordon.flickster.adapters.MovieAdapters;
import com.isaacngordon.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {

    private static final String API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static final String MOVIE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY;

    List<Movie> movies;

//    Using a RecyclerView has the following key steps:
//
//    Add RecyclerView support library to the gradle build file
//    Define a model class to use as the data source
//    Add a RecyclerView to your activity to display the items
//    Create a custom row layout XML file to visualize the item
//    Create a RecyclerView.Adapter and ViewHolder to render the item
//    Bind the adapter to the data source to populate the RecyclerView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        //get view
        RecyclerView rvMovies = findViewById(R.id.rvMovies);

        //set up the adapter for th RecyclerView
        movies = new ArrayList<>();                                             //dummy so we dont have issues
        final MovieAdapters adapter = new MovieAdapters(this, movies);
        rvMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMovies.setAdapter(adapter);

        //create an asyncronous http connection and and get the JSON data from MOVIE_URL
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MOVIE_URL, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray movieJSONArray = response.getJSONArray("results");              //get results in JSONArray
                    movies.addAll(Movie.fromJsonArray(movieJSONArray));                             //add all movies from JSONArray to the movies list
                    adapter.notifyDataSetChanged();                                                 //notify the adapter of new changes
                    Log.d("smile", movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }//catch
            }//onSuccess

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }//onFailure
        });//JsonHttpResponseHandler

    }//onCreate
}//class
