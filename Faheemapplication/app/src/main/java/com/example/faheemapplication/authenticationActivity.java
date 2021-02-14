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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.quickstart.auth.R;
import com.google.firebase.quickstart.auth.databinding.ActivityPhoneAuthBinding;

import java.util.concurrent.TimeUnit;
public class authenticationActivity extends AppCompatActivity {

    EditText phoneInput;
    Button actionButton;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        getSupportActionBar().hide(); // Hide the action bar in the screen
        mAuth = FirebaseAuth.getInstance();

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
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    public void mainActivity() {
        Intent intent = new Intent(this, mainScreen.class);
        startActivity(intent);
    }
    public void otpActivity(){
        Intent Otpintent = new Intent(this, authenticationOTPActivity.class);
        startActivity(Otpintent);
    }
    ////
}