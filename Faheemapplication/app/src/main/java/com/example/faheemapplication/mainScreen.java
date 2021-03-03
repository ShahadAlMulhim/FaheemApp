package com.example.faheemapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class mainScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getSupportActionBar().hide(); // Hide the action bar in the screen

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        //// ** Adding the icons in the buttons **
        // Scan product button
        Drawable scanProductDrawable = ContextCompat.getDrawable(mainScreen.this,R.drawable.scan_product); // Get the drawable icon
        scanProductDrawable.setBounds(0, 0, 150, 150); // Set the hight and width of the icon
        Button scanProductButton = findViewById(R.id.scanProduct); // Get the button that will set the icon on it
        scanProductButton.setCompoundDrawables(null, null, scanProductDrawable, null); // Set the drawable in the right of the button

        // Story button
        Drawable storyDrawable = ContextCompat.getDrawable(mainScreen.this,R.drawable.story);
        storyDrawable.setBounds(0, 0, 150, 150);
        Button storyButton = findViewById(R.id.story);
        storyButton.setCompoundDrawables(null, null, storyDrawable, null);

        // Game button
        Drawable gameDrawable = ContextCompat.getDrawable(mainScreen.this,R.drawable.game);
        gameDrawable.setBounds(0, 0, 150, 150);
        Button gameButton = findViewById(R.id.game);
        gameButton.setCompoundDrawables(null, null, gameDrawable, null);



        ////
        // When click on "Scan product" button take the user to authentication activity
        scanProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentUser == null){
                    Intent authIntent = new Intent(mainScreen.this, authenticationActivity.class);
                    authIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    authIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(authIntent);
                    finish();
                } else{
                    Intent intent = new Intent(mainScreen.this, checkProductActivity.class); // the intent provide the external class which the "authenticationActivity" be invoked
                    startActivity(intent);
                }
            }
        });
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoryActivity();
            }
        });
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameActivity();
            }
        });
    }
    public void StoryActivity(){ // Open story activity
        Intent intent = new Intent(this, StoryActivity.class);
        startActivity(intent);
    }
    public void gameActivity(){ // Open game activity
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}