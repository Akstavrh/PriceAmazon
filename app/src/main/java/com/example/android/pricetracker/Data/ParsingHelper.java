package com.example.android.pricetracker.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by e.delcampo on 06/03/2018.
 */

//TODO implement all data parsing methods (Product, price, image, ASIN)

public class ParsingHelper {

    public ParsingHelper() {
    }

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36";

    public static String parseAddress(String url) {

        String title = null;

        try {
            Document doc = Jsoup.connect(url).get();
            title = doc.title();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return title;
    }

    public static String parseProduct(String url) {

        String product = null;
        try {
            Document doc = Jsoup.connect(url)
            .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
            .referrer("http://www.google.com")
            .get();
            product = doc.toString();

            //Parse product name ->OK
            //Element prodEl = doc.select("span#productTitle").first();
            //String prod = prodEl.text();

            //Parse price and currency ->NO
            //Elements priceEl = doc.select("span.a-size-medium.a-color-price.offer-price.a-text-normal");


            //NO

            Elements priceEl = doc.select("span#priceblock_ourprice.a-size-medium.a-color-price");

            String prod = priceEl.text();

           if (prod == null) {
                product = "NULL";
            } else {
                product = prod.toString();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return product;
    }
}
