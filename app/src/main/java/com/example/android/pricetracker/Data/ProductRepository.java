package com.example.android.pricetracker.Data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by e.delcampo on 10/04/2018.
 * A Repository is a class that abstracts access to multiple data sources. The Repository is not part
 * of the Architecture Components libraries, but is a suggested best practice for code separation and
 * architecture. A Repository class handles data operations. It provides a clean API to the rest of
 * the app for app data.
 */

public class ProductRepository {

    //Add member variables for the DAO and the list of words.
    private ProductDao mProdDao;
    private LiveData<List<Products>> mAllProducts;


    //Add a constructor that gets a handle to the database and initializes the member variables.
    public ProductRepository(Application application) {

        ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
        mProdDao = db.productDao();
        mAllProducts = mProdDao.getAllProducts();
    }

    //Add a wrapper for getAllWords(). Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    LiveData<List<Products>> getAllProducts() {
        return mAllProducts;
    }

    //Add a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash. Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
    public void insert (Products products){
        new insertAsyncTask(mProdDao).execute(products);
    }


    private static class insertAsyncTask extends AsyncTask<Products, Void, Void> {

        private ProductDao mAsyncProductDao;

        insertAsyncTask(ProductDao dao) {
            mAsyncProductDao = dao;
        }

        @Override
        protected Void doInBackground(Products... products) {
            mAsyncProductDao.insert(products[0]);
            return null;
        }
    }
}
