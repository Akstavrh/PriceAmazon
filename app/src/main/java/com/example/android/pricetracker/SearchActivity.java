package com.example.android.pricetracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.android.pricetracker.Utils.ParsingHelper;

;

public class SearchActivity extends AppCompatActivity {


    //TODO Implement HOme button

    String url = null;
    FloatingActionButton floatingButton;



    private WebView webView;
    private final String BASIC_URL = "https://www.amazon.it/s/ref=nb_sb_noss_2?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&url=search-alias%3Daps&field-keywords=";
    private final String AFTER_URL = "&rh=i%3Aaps%2Ck%3A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        View bottomNavBarLayout = findViewById(R.id.include_bottom_nav_view);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) bottomNavBarLayout.findViewById(R.id.nav_bar);

        //inflate Fab
        floatingButton = (FloatingActionButton) findViewById(R.id.action_add_to_favorites);


        //identify WebView in layout
        webView = (WebView) findViewById(R.id.webView);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String searchString = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //WebView settings
        webView.setWebViewClient(new PriceTrackerWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        //Create the Url
        url = BASIC_URL + searchString + AFTER_URL;

        //load the url into webView
        webView.loadUrl(url);

        //Fab oncdlickListener
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlToParse = webView.getUrl();
                new parseAddressTask().execute(urlToParse);

            }


        });

        //bottom navbar listener
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.action_home:
                                Intent homeIntent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(homeIntent);
                                return true;
                            case R.id.action_favorites:
                                Intent favIntent = new Intent(getBaseContext(), FavoritesActivity.class);
                                startActivity(favIntent);
                                return true;
                            case R.id.action_offers:
                                return true;
                        }

                        return true;
                    }
                }
        );

    }

    //Inflate the Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Handle click on menu items


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_forward:
                if (webView.canGoForward()){
                    webView.goForward();
                }
                return true;
            case R.id.action_back:
                if (webView.canGoBack()){
                    webView.goBack();
                }
                return true;
            case R.id.action_refresh:
                webView.reload();
        }

        return super.onOptionsItemSelected(item);
    }

    //inner class to handle the Async Tasks (url parsing)
    private class parseAddressTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            //call to parsingHelper methods
            String returnedTitle = ParsingHelper.parseReviews(url);
            return returnedTitle;
        }

        @Override
        protected void onPostExecute(String s) {

            //Testing ToastMessage
            //TODO replace Toast with data to be passed to Favorites screen
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    }


//inner WebViewClient class to retrieve the current url
    public class PriceTrackerWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //Toast toast = Toast.makeText(SearchActivity.this, "search", Toast.LENGTH_SHORT);

            if (url.toLowerCase().contains("search")){
               // toast.show();
                makeFabInvisible();

            } else {

                makeFabVisible();

            }
        }
    }

    //Helper method make Fab invisible
private void makeFabInvisible(){
        floatingButton.setVisibility(View.INVISIBLE);
}

    //Helper method make Fab visible
    private void makeFabVisible(){
        floatingButton.setVisibility(View.VISIBLE);
    }

}

