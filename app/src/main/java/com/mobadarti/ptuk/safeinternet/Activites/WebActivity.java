package com.mobadarti.ptuk.safeinternet.Activites;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.mobadarti.ptuk.safeinternet.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setTitle(getIntent().getExtras().getString("title"));
        WebView webView = findViewById(R.id.webview);
        String link = getIntent().getExtras().getString("link");
        if(link.contains("pdf"))
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+ link);
        else webView.loadUrl(link);
    }

}
