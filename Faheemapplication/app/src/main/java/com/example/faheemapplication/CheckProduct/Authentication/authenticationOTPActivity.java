package com.example.faheemapplication.CheckProduct.Authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;

import com.example.faheemapplication.CheckProduct.AllergyType.AllergyTypeActivity;
import com.example.faheemapplication.CheckProduct.AllergyType.checkProductActivity;
import com.example.faheemapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import com.chaos.view.PinView;


public class authenticationOTPActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private String mAuthVerificationId;
    private TextView mOtpFeedback;
    PinView OTPCode;
    Button verifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_otp);
        getSupportActionBar().hide(); // Hide the action bar in the screen


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mAuthVerificationId=getIntent().getStringExtra("AuthCredentials");

        mOtpFeedback=findViewById(R.id.textView5);
        verifyButton = findViewById(R.id.actionButton);
        OTPCode = findViewById(R.id.OTP_Pin);

        // When click on back button take the user back to the main screen
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticationActivity(); // call authenticationActivity function so when user click the button the authenticationActivity is opened
            }
        });

        verifyButton.setEnabled(false); // By default make the button disable until the condition in the if statement is triggered
        OTPCode.addTextChangedListener(new TextWatcher() { // Use TextWatcher method to track any changes in the textEdit
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 6){
                    verifyButton.setEnabled(true);
                }
                else
                    verifyButton.setEnabled(false);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        // Verification otpCode
        // String otp = String.valueOf(OTPCode);
        verifyButton.setOnClickListener(v -> {
            String newString = OTPCode.getText().toString();
            if(newString.isEmpty()){
                mOtpFeedback.setText("please fill the form again");
            }
            else{
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mAuthVerificationId, newString);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        allergyTypeActivity();
//                        FirebaseUser mCurrentUser = task.getResult().getUser();
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) { // The verification code entered was invalid
                            mOtpFeedback.setVisibility(View.VISIBLE);
                            mOtpFeedback.setText("رمز التحقق المدخل غير صحيح!");
                            mOtpFeedback.setBackground(ContextCompat.getDrawable(authenticationOTPActivity.this, R.drawable.error_label));

                            Drawable ErrorDrawable = ContextCompat.getDrawable(authenticationOTPActivity.this, R.drawable.error);
                            ErrorDrawable.setBounds(0, 0, 90, 90);
                            TextView errorLabel = findViewById(R.id.textView5);
                            errorLabel.setCompoundDrawables(null, null, ErrorDrawable, null);


                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mCurrentUser != null) {
       sendUSerToCheckproduct();
        }
    }

    public void sendUSerToCheckproduct(){
        Intent homeIntent = new Intent(authenticationOTPActivity.this, checkProductActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }

    public void authenticationActivity() {
        Intent intent = new Intent(this, authenticationActivity.class);
        startActivity(intent);
    }

    public void allergyTypeActivity(){
        Intent intent = new Intent(this, AllergyTypeActivity.class);
        startActivity(intent);
    }


}