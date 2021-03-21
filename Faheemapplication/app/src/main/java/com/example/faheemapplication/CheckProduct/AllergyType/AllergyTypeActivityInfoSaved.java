package com.example.faheemapplication.CheckProduct.AllergyType;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.faheemapplication.HomeScreen.mainScreen;
import com.example.faheemapplication.R;

public class AllergyTypeActivityInfoSaved extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_type_info_saved);
        getSupportActionBar().hide(); // Hide the action bar in the screen

        // When click on back button take the user back to the main screen
        ImageView closeButton = findViewById(R.id.closeButton);
        Button scanProductButton = findViewById(R.id.scanProduct);
        Button mainScreenButton = findViewById(R.id.mainScreen);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            }
        });
        mainScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            }
        });
        scanProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaptureProduct();
            }
        });
    }

    public void mainActivity() {
        Intent intent = new Intent(this, mainScreen.class);
        startActivity(intent);
    }
    public void CaptureProduct(){
        Intent intent = new Intent(this, checkProductActivity.class);
        startActivity(intent);
    }
}