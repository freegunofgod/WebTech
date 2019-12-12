package com.example.webtech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Song song = (Song) getIntent().getSerializableExtra("SONG");
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl(song.getUrl());
        
        getSupportActionBar().setTitle(song.getName());
        getSupportActionBar().setSubtitle(song.getTitle());
    }

}