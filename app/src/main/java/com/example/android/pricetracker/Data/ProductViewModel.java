package com.example.android.pricetracker.Data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by e.delcampo on 10/04/2018.
 * A ViewModel holds your app's UI data in a lifecycle-conscious way that survives configuration changes.
 * Separating your app's UI data from your Activity and Fragment classes lets you better follow
 * the single responsibility principle: Your activities and fragments are responsible for drawing
 * data to the screen, while your ViewModel can take care of holding and processing all the data
 * needed for the UI.

 In the ViewModel, use LiveData for changeable data that the UI will use or display.
 Using LiveData has several benefits:

 You can put an observer on the data (instead of polling for changes) and only update the
 the UI when the data actually changes.
 The Repository and the UI are completely separated by the ViewModel. There are no database calls from the ViewModel, making the code more testable.
 */

public class ProductViewModel extends AndroidViewModel {

    //Add a private member variable to hold a reference to the repository.
    private ProductRepository productRepository;

    //Add a private LiveData member variable to cache the list of words.
    private LiveData<List<Products>> mAllProducts;

    //Add a constructor that gets a reference to the repository and gets the list of words
    // from the repository.
    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        mAllProducts = productRepository.getAllProducts();
    }

    //Add a "getter" method for all the words. This completely hides the implementation from the UI.
    LiveData<List<Products>> getmAllWords(){
        return mAllProducts;
    }

    //Create a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is completely hidden from the UI.
    public void insert(Products products) {
        productRepository.insert(products);
    }
}
