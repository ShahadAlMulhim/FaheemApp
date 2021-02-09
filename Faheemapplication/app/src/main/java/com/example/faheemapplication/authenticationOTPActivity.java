package com.example.faheemapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class authenticationOTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_o_t_p);
        getSupportActionBar().hide(); // Hide the action bar in the screen


        ////
        // When click on back button take the user back to the main screen
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticationActivity(); // call authenticationActivity function so when user click the button the authenticationActivity is opened
            }
        });
    }
    public void authenticationActivity() {
        Intent intent = new Intent(this, authenticationActivity.class);
        startActivity(intent);
    }
    ////
}