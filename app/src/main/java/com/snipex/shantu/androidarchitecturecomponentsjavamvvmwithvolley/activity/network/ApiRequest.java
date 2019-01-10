package com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.network;

import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.response.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    /*
     * We would be using the below url:
     * https://newsapi.org/v2/everything?q=movies&apiKey=...&pageSize=10&page=1
     * The url has four query parameters.
     * First Query, q is for movies as we want movies articles. if you want different article you can change inside ArticleMovieConstants
     * Second Query, apiKey will be your api key for newsapi.org. just register yourself and you will get an apiKey
     * Third Query, pageSize is configurable. we can change page size inside ArticleMovieConstants
     * Fourth Query, page will be updated inside ArticleDataSource ( loadAfter ) whenever user scroll , though first page number is inside ArticleMovieConstants. you can change page number if you want.
     */

    @GET("v2/everything/")
    Call<ArticleResponse> getMovieArticles(
            @Query("q") String query,
            @Query("apikey") String apiKey,
            @Query("pageSize") int pageSize,
            @Query("page") int page
    );
}
