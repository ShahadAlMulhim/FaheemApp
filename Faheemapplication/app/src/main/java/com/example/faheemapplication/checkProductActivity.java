package com.example.faheemapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class checkProductActivity extends AppCompatActivity {

    private Button actionButton;
    private Button logout;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_product);

        logout = (Button) findViewById(R.id.actionButton2);
        actionButton = (Button) findViewById(R.id.actionButton);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                sendUserToMaikscreen();
                }
        });

        // When click on back button take the user back to the main screen
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            }
        });
    }

    private void sendUserToMaikscreen(){
        Intent AuthIntent = new Intent(checkProductActivity.this, mainScreen.class);
        AuthIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        AuthIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(AuthIntent);
        finish();
    }

    public void mainActivity() {
        Intent intent = new Intent(this, mainScreen.class);
        startActivity(intent);
    }
}