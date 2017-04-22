package com.williamsumitromytextview.pikachisemarket.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.williamsumitromytextview.pikachisemarket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivacyFragment extends android.support.v4.app.Fragment {


    public PrivacyFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        WebView wBrowser  = (WebView) findViewById(R.id.webView1);
//        wBrowser.getSettings().setJavaScriptEnabled(true);
//        //wBrowser.loadUrl("http://www.google.com");
//        wBrowser.loadUrl("file:///android_asset/Term_Condition.html");
        return inflater.inflate(R.layout.fragment_privacy, container, false);

    }

}
