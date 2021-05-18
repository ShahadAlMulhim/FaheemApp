package com.example.faheemapplication.CheckProduct.Authentication;

import androidx.appcompat.app.AppCompatActivity;

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

import androidx.annotation.NonNull;

import com.example.faheemapplication.CheckProduct.AllergyType.AllergyTypeActivity;
import com.example.faheemapplication.CheckProduct.AllergyType.checkProductActivity;
import com.example.faheemapplication.HomeScreen.mainScreen;
import com.example.faheemapplication.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;


public class authenticationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

   private EditText phoneInput;
   private EditText CountryCode;
   private Button actionButton;
   // private static final String TAG = "Error!";
  


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        getSupportActionBar().hide(); // Hide the action bar in the screen

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();

        // Make the button disable and enable based on textEdit changes
        phoneInput = (EditText) findViewById(R.id.editTextPhone);
        CountryCode = (EditText) findViewById(R.id.editTextCode);
        actionButton = (Button) findViewById(R.id.actionButton);

        CountryCode.setText("+966 "); // set the country code in the phone field to be fixed and uneditable
        Selection.setSelection(CountryCode.getText(), CountryCode.getText().length()); // set the insertion point to a specific location within a phone field
        actionButton.setEnabled(false); // set the buttons disabled by default

        phoneInput.addTextChangedListener(new TextWatcher() { // Use TextWatcher method to track any changes in the textEdit
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    actionButton.setEnabled(false);
                }
                else
                    actionButton.setEnabled(true);
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith("+966 ")){
                    CountryCode.setText("+966 ");
                    Selection.setSelection(CountryCode.getText(), CountryCode.getText().length()); // Set the insertion point after the 5 character
                }
            }
        });


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String Country=CountryCode.getText().toString();
             String Phone=phoneInput.getText().toString();
             String completePhone =Country+Phone;

                if (mCurrentUser == null){
                   PhoneAuthProvider.getInstance().verifyPhoneNumber(
                         completePhone,
                           120,
                           TimeUnit.SECONDS,
                           authenticationActivity.this,
                           mCallbacks
                   ); //send otp

                    Intent otpIntent = new Intent(authenticationActivity.this, authenticationOTPActivity.class);
                    startActivity(otpIntent);
                    finish();
                }
            }
        });

        mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }
            @Override
            public void onCodeSent( String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Intent otpIntent = new Intent(authenticationActivity.this, authenticationOTPActivity.class);
                otpIntent.putExtra("AuthCredentials",s);
                startActivity(otpIntent);
            } //when sent otp go to OTP page

        };

        // When click on back button take the user back to the main screen
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            }
        });
    }

    /* public void onClick(View v) {
        SafetyNet.getClient(this).verifyWithRecaptcha(API_KEY)
                .addOnSuccessListener((Executor) this,
                        new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                // Indicates communication with reCAPTCHA service was
                                // successful.
                                String userResponseToken = response.getTokenResult();
                                if (!userResponseToken.isEmpty()) {
                                    // Validate the user response token using the
                                    // reCAPTCHA siteverify API.
                                }
                            }
                        })
                .addOnFailureListener((Executor) this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            // An error occurred when communicating with the
                            // reCAPTCHA service. Refer to the status code to
                            // handle the error appropriately.
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            Log.d(TAG, "Error: " + CommonStatusCodes
                                    .getStatusCodeString(statusCode));
                        } else {
                            // A different, unknown type of error occurred.
                            Log.d(TAG, "Error: " + e.getMessage());
                        }
                    }
                });
    } */

    @Override
    protected void onStart() {
        super.onStart();
        if (mCurrentUser != null) {
            Intent homeIntent = new Intent(authenticationActivity.this, checkProductActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(homeIntent);
            finish();
        }
    }

        public void mainActivity() {
        Intent intent = new Intent(this, mainScreen.class);
        startActivity(intent);
    }

    /* public void sendUSerToCheckproduct(){
        Intent homeIntent = new Intent(authenticationActivity.this, checkProductActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    } */

    public void allergyTypeActivity(){
        Intent intent = new Intent(this, AllergyTypeActivity.class);
        startActivity(intent);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        allergyTypeActivity();
//                        FirebaseUser mCurrentUser = task.getResult().getUser();
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid

                        }
                    }
                });
    }
}
