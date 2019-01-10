package com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.data_source;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.snipex.shantu.androidarchitecturecomponentspagingwithjava.activity.model.Article;

public class ArticleDataSourceFactory extends DataSource.Factory {

    /**
     * ArticleDataSourceFactory is responsible for retrieving the data using the
     * ArticleDataSource
     * and
     * PagedList configuration which is inside our ArticleViewModel class
     */


    // creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, Article>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Article> create() {
        //getting our data source object
        ArticleDataSource articleDataSource = new ArticleDataSource();

        //posting the data source to get the values
        itemLiveDataSource.postValue(articleDataSource);

        //returning the data source
        return articleDataSource;
    }

    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, Article>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
