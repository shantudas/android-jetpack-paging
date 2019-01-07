package com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.R;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.adapter.MoviesAdapter;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.model.Article;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.network.ApiRequest;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.network.RetrofitRequest;
import com.snipex.shantu.androidarchitecturecomponentsjavamvvmwithvolley.activity.response.ArticleResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private LinearLayoutManager layoutManager;
    private MoviesAdapter adapter;
    private ArrayList<Article> articleArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * https://medium.com/@amtechnovation/android-architecture-component-mvvm-part-1-a2e7cff07a76
         * */

        initialization();

        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);


        Call<ArticleResponse> call = apiRequest.getMovieArticles("movies", "079dac74a5f94ebdb990ecf61c8854b7");
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                Log.d(TAG, "onResponse response:: " + response);

                if (response.body() != null) {

                    articleArrayList = new ArrayList<>(response.body().getArticles());

                    Log.d(TAG, "articles status:: " + response.body().getStatus());
                    if (response.body().getStatus().equals("ok")){

                        // specify an adapter
                        adapter = new MoviesAdapter(MainActivity.this, articleArrayList);
                        my_recycler_view.setAdapter(adapter);
                    }


                    Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                    Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                    Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                }

            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.d(TAG, "onFailure response :: " + t);
            }

        });
    }

    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);


    }
}
