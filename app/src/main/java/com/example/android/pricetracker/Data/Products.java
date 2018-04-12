package com.example.android.pricetracker.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by e.delcampo on 04/04/2018.
 */

@Entity(tableName = "products")
public class Products {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "asin")
    private String asin;

    @ColumnInfo(name = "product")
    private String product;

    @ColumnInfo(name = "reviews")
    private String reviews;

    @ColumnInfo(name = "imageUrl")
    private String imageUrl;

    @ColumnInfo(name = "price")
    private String price;


    public Products(@NonNull String asin, String product, String reviews, String imageUrl, String price) {
        this.asin = asin;
        this.product = product;
        this.reviews = reviews;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    @NonNull
    public String getAsin() {
        return asin;
    }

    public void setAsin(@NonNull String asin) {
        this.asin = asin;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
