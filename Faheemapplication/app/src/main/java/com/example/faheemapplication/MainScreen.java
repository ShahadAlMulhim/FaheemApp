package com.example.faheemapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().hide();

        // ** Adding the icons in the buttons **
        // Scan product button
        Drawable scanProductDrawable = ContextCompat.getDrawable(MainScreen.this,R.drawable.scan_product); // Get the drawable icon
        scanProductDrawable.setBounds(0, 0, 150, 150); // Set the hight and width of the icon
        Button scanProductButton = findViewById(R.id.scanProduct); // Get the button that will set the icon on it
        scanProductButton.setCompoundDrawables(null, null, scanProductDrawable, null); // Set the drawable in the right of the button

        // Story button
        Drawable storyDrawable = ContextCompat.getDrawable(MainScreen.this,R.drawable.story);
        storyDrawable.setBounds(0, 0, 150, 150);
        Button storyButton = findViewById(R.id.story);
        storyButton.setCompoundDrawables(null, null, storyDrawable, null);

        // Game button
        Drawable gameDrawable = ContextCompat.getDrawable(MainScreen.this,R.drawable.game);
        gameDrawable.setBounds(0, 0, 150, 150);
        Button gameButton = findViewById(R.id.game);
        gameButton.setCompoundDrawables(null, null, gameDrawable, null);

    }
}