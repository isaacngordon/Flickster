package com.isaacngordon.flickster.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
public class Movie {
    String posterPath;
    String title;
    String overview;
    String backdropPath;
    double avgRating;
    int movieID;

    /**
     * Creates movie object from data in a jsonObject.
     * @param jsonObject
     * @throws JSONException
     */
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        avgRating = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");
    }//constructor

    //empty constructor for the Parceler Library
    public Movie(){

    }

    /**
     * Static method that generate s List of Movies from the inputted jsonArray.
     * @param movieJsonArray
     * @return
     */
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie>  movieList = new ArrayList<>();
        for(int i = 0; i < movieJsonArray.length(); i++){
            movieList.add(new Movie(movieJsonArray.getJSONObject(i)));
        }//for

        return movieList;
    }//fromJsonArray

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public double getVoteAverage(){
        return avgRating;
    }

    public String getBackdropPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public int getMovieId(){
        return movieID;
    }
}
