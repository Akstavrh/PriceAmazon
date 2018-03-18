package com.example.android.pricetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.pricetracker.MESSAGE";
    private EditText mSearchEditText;
    private Button mSearchButton;

    //TODO Create a db to save product data and prices (with all helper methods and classes)
            //TODO Create a new activity to show db data (Recycler View)

    //TODO Implement automatic update of db data
    // TODO Handle notifications
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchEditText= (EditText) findViewById(R.id.et_search);
        mSearchButton = (Button) findViewById(R.id.bt_search);


        //OnClickListener
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                String message = mSearchEditText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }


}
