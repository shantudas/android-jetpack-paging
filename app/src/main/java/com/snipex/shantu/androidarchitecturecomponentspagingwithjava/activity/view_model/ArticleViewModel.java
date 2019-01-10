package com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.util.Log;

import com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.constants.ArticleMovieConstants;
import com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.data_source.ArticleDataSourceFactory;
import com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.model.Article;

public class ArticleViewModel extends ViewModel {

    /**
     * ArticleViewModel class is responsible for
     * PagedList configuration
     * and
     * building the PagedList
     */

    private static final String TAG = ArticleViewModel.class.getSimpleName();

    public LiveData<PagedList<Article>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;

    public ArticleViewModel() {
        Log.d(TAG, "ArticleViewModel called");

        ArticleDataSourceFactory articleDataSourceFactory = new ArticleDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = articleDataSourceFactory.getItemLiveDataSource();

        // Getting PagedList configuration
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setPageSize(ArticleMovieConstants.PAGE_SIZE).build();

        // Building the paged list
        itemPagedList = (new LivePagedListBuilder(articleDataSourceFactory, pagedListConfig)).build();
    }
}
