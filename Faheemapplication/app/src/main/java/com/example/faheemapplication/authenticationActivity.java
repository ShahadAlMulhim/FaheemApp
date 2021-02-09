package com.example.faheemapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class authenticationActivity extends AppCompatActivity {

    EditText phoneInput;
    Button actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        getSupportActionBar().hide(); // Hide the action bar in the screen


        ////
        // Make the button disable and enable based on textEdit changes
        phoneInput = (EditText) findViewById(R.id.editTextPhone);
        actionButton = (Button) findViewById(R.id.actionButton);
        actionButton.setEnabled(false); // By default make the button disable until the condition in the if statement is triggered

        phoneInput.setText("+966 "); // set the country code in the phone field to be fixed and uneditable
        Selection.setSelection(phoneInput.getText(), phoneInput.getText().length()); // set the insertion point to a specific location within a phone field

        phoneInput.addTextChangedListener(new TextWatcher() { // Use TextWatcher method to track any changes in the textEdit
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 14){
                    actionButton.setEnabled(true);
                }
                else
                    actionButton.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith("+966 ")){
                    phoneInput.setText("+966 ");
                    Selection.setSelection(phoneInput.getText(), phoneInput.getText().length()); // Set the insertion point after the 5 character
                }
            }
        });
        ////


        ////
        // When click on back button take the user back to the main screen
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            }
        });
        Button sendButton = findViewById(R.id.actionButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            }
        });
    }
    public void mainActivity() {
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
    public void otpActivity(){
        Intent Otpintent = new Intent(this, authenticationOTPActivity.class);
        startActivity(Otpintent);
    }
    ////
}