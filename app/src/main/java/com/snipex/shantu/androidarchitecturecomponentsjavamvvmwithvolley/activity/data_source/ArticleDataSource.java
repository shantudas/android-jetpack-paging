package com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.data_source;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.constants.ArticleMovieConstants;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.model.Article;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.network.RetrofitRequest;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.response.ArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDataSource extends PageKeyedDataSource<Integer, Article> {

    private static final String TAG = ArticleDataSource.class.getSimpleName();

    /*
     * This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Article> callback) {
        Log.d(TAG, "loadInitial called");
        RetrofitRequest.getInstance().getApi()
                .getMovieArticles(ArticleMovieConstants.QUERY, ArticleMovieConstants.API_KEY, ArticleMovieConstants.PAGE_SIZE, ArticleMovieConstants.FIRST_PAGE)
                .enqueue(new Callback<ArticleResponse>() {

                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        Log.d(TAG + " response :: ", String.valueOf(response));
                        if (response.body() != null) {
                            callback.onResult(response.body().getArticles(), null, ArticleMovieConstants.FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        Log.d(TAG, String.valueOf(t));
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {

    }

    /*
     * This method it is responsible for the subsequent call to load the data page wise when user scroll
     * This method is executed in the background thread
     * We are fetching the next page data from the api
     * and passing it via the callback method to the UI.
     * The "params.key" variable will have the updated value.
     */

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {
        RetrofitRequest.getInstance().getApi()
                .getMovieArticles(ArticleMovieConstants.QUERY, ArticleMovieConstants.API_KEY, ArticleMovieConstants.PAGE_SIZE, params.key)
                .enqueue(new Callback<ArticleResponse>() {

                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        Log.d(TAG + " response :: ", String.valueOf(response));
                        if (response.body() != null) {

                            Log.d(TAG, "params Key :: " + params.key + " params load size - " + params.requestedLoadSize);

                            //if the response getTotalResults is not null then
                            //incrementing the next page number
                            Integer key = (params.key == response.body().getTotalResults()) ? null : params.key + 1;

                            callback.onResult(response.body().getArticles(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        Log.d(TAG, String.valueOf(t));
                    }
                });
    }
}
