package com.example.mygreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ScreenSplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screensaver);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(ScreenSplashActivity.this,MainActivity.class);
                ScreenSplashActivity.this.startActivity(mainIntent);
                ScreenSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}