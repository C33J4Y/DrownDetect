package com.example.mymobileapplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class perimeter_camView extends AppCompatActivity {
    Button switchViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perimeter_cam_view);

        switchViewButton = findViewById(R.id.switchViewButton);

        WebView webView = new WebView(this);
        webView.loadUrl("http://172.20.10.6:8123");
        webView.setInitialScale(3);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        setContentView(webView);

        switchViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), activity_portal.class);
                startActivity(intent);
            }
        });
    }
}