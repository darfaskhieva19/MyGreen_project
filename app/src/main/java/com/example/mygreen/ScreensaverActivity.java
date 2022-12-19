package com.example.mygreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ScreensaverActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(ScreensaverActivity.this,MainActivity.class);
                ScreensaverActivity.this.startActivity(mainIntent);
                ScreensaverActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}