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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private LinearLayoutManager layoutManager;
    private MoviesAdapter adapter;

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

        // specify an adapter
        adapter = new MoviesAdapter(MainActivity.this);
        my_recycler_view.setAdapter(adapter);

    }
}
