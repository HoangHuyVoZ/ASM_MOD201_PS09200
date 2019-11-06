package com.huynhps09200.asm_mod201;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoadNewsActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_news);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Tin tức giáo dục");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        Intent intent=getIntent();
        String link=intent.getStringExtra("link");
        webView=findViewById(R.id.Web);
        webView.loadUrl(link);
    }
}
