package com.example.android.pricetracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by e.delcampo on 15/03/2018.
 */

//TODO Animate cancel button
    //TODO Add Swipe functions.
    //TODO add review graphic


public class FavoritesActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_row);
    }
}
