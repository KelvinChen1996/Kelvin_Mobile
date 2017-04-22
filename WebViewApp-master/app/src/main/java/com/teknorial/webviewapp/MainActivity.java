package com.teknorial.webviewapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView wBrowser = (WebView) findViewById(R.id.webView);
        wBrowser.getSettings().setJavaScriptEnabled(true);
        //wBrowser.loadUrl("http://www.google.com");
        wBrowser.loadUrl("file:///android_asset/Term_Condition.html");


    }


}