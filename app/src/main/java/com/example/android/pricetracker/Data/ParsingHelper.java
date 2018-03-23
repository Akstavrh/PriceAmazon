package com.example.android.pricetracker.Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by e.delcampo on 06/03/2018.
 */

//TODO implement all data parsing methods (image, reviews)

public class ParsingHelper {

    public ParsingHelper() {
    }

    private static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";

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
            .userAgent(USER_AGENT)
            //.referrer("http://www.google.com")
            .get();
            product = doc.toString();

            //Parse product name ->OK
            Element prodEl = doc.select("span#productTitle").first();
            String prod = prodEl.text();

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

    // This is the method to correctly parse the product price
    public static String parsePrice(String url) {

        String product = null;
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent(USER_AGENT)
                    //.referrer("http://www.google.com")
                    .get();
            product = doc.toString();

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


    // this is the method to correctly parse ProductCode from amazon url
    public static String parseAsin(String url){
        String roughProdCode = url.substring(url.indexOf("d/"),url.indexOf("/ref"));

        String cleanProdCode = roughProdCode.substring(2);

        return cleanProdCode;
    }
}
