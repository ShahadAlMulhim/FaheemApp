package com.example.faheemapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class mainActivity extends AppCompatActivity {
    private static int SPLASH_DELAY = 1700; // The time that the splash screen will take to disappear
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // Hide the action bar in the screen


        ////
        // Make Splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mainActivity.this, mainScreen.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
        ////
    }

}
