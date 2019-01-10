package com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.snipex.shantu.androidarchitecturecomponentspagingwithjava.R;
import com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.adapter.MovieAdapter;
import com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.view_model.ArticleViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private LinearLayoutManager layoutManager;
    private MovieAdapter adapter;
    ArticleViewModel articleViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
         * Resources :: for help to create this project
         *
         * Source-1             ::  https://medium.com/@amtechnovation/android-architecture-component-mvvm-part-1-a2e7cff07a76
         * source description   ::  for MVVM pattern using  live data and view model by help of retrofit
         *
         * Source -2            ::  https://proandroiddev.com/8-steps-to-implement-paging-library-in-android-d02500f7fffe
         * source description   ::  for paging
         *
         * Source -3            :: https://proandroiddev.com/8-steps-to-implement-paging-library-in-android-d02500f7fffe
         * source description   :: complete tutorial for paging
         *  */


        initialization();

        getMovieArticles();

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

        // adapter
        adapter = new MovieAdapter(MainActivity.this);

        // View Model
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
    }

    /**
     * get movies articles from news api
     *
     * @param @null
     */
    private void getMovieArticles() {

        articleViewModel.itemPagedList.observe(this, articles -> {

            //submitting the articles to adapter
            adapter.submitList(articles);
        });

        // finally setting adapter to the recyclerView
        my_recycler_view.setAdapter(adapter);
    }
}
