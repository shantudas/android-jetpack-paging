package com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.network;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.response.ArticleResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("v2/everything/")
    Call<ArticleResponse> getMovieArticles(
            @Query("q") String query,
            @Query("apikey") String apiKey
    );
}
