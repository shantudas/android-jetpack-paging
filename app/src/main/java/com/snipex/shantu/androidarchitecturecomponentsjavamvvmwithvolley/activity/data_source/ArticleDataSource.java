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

                            Log.d(TAG, "articles size on load:: " + response.body().getArticles().size());

                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Article> callback) {
        RetrofitRequest.getInstance().getApi()
                .getMovieArticles(ArticleMovieConstants.QUERY, ArticleMovieConstants.API_KEY, ArticleMovieConstants.PAGE_SIZE, params.key)
                .enqueue(new Callback<ArticleResponse>() {

                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        Log.d(TAG + " response :: ", String.valueOf(response));
                        if (response.body() != null) {

                            Log.d(TAG,"params Key :: "+params.key+" params load size - "+params.requestedLoadSize);
                            //incrementing the next page number
                            Integer key =  params.key + 1;

                            callback.onResult(response.body().getArticles(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {

                    }
                });
    }
}
