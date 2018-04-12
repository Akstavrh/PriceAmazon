package com.example.android.pricetracker.Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by e.delcampo on 04/04/2018.
 * In the DAO (data access object), you specify SQL queries and associate them with method calls.
 * The compiler checks the SQL and generates queries from convenience annotations for common queries, such as @Insert.
 * The DAO must be an interface or abstract class.
 * By default, all queries must be executed on a separate thread.
 * Room uses the DAO to create a clean API for your code.
 *
 */



@Dao
public interface ProductDao {

    @Insert
    void insert(Products products);

    @Query("DELETE FROM Products")
    void deleteAll();

    @Query("SELECT * from Products ORDER BY asin ASC")
    LiveData<List<Products>> getAllProducts();

}
