package com.isaacngordon.flickster.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Movie {
    String posterPath;
    String title;
    String overview;

    /**
     * Creates movie object from data in a jsonObject.
     * @param jsonObject
     * @throws JSONException
     */
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }//constructor

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

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
