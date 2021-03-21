package com.example.faheemapplication.CheckProduct.AllergyType;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faheemapplication.HomeScreen.mainScreen;
import com.example.faheemapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class checkProductActivity extends AppCompatActivity {

    Button actionButton;
    Button scanBtn;
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

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this::onClick);
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


    public void onClick(View v) {
        scanCode();
    }

    private void scanCode(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null ){// User is signed in
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setCaptureActivity(CaptureAct.class);
            integrator.setOrientationLocked(false);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setPrompt("Scanning Code");
            integrator.initiateScan();
        }else {// No user is signed in
            //Log.d("Exc ","you must sign in");
            Context context = getApplicationContext();
            CharSequence text = "عليك ان تسجل الدخول";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        }
    }


    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference foods = database.getReference("Foods"); //table foods
        DatabaseReference childInfo = database.getReference("child_info"); //table child_info
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){// User is signed in
            String uid = user.getUid();
            try {
                IntentResult result = IntentIntegrator.parseActivityResult(reqCode,resCode,data);
                if(result != null){
                    if(result.getContents() != null){// if it read Successfully (barcode)
                        foods.addValueEventListener(new ValueEventListener() { // a reference to Foods table
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) { // to query foods table
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //start for loop
                                    if(snapshot.child("barcode").getValue().equals(result.getContents())){ //read barcore and get result
                                        String  productName = snapshot.child("name").getValue().toString();// get the name of a given food barcode
                                        childInfo.addValueEventListener(new ValueEventListener() { // a reference to child_info table
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) { // to query child_info table
                                                for (DataSnapshot data : dataSnapshot1.getChildren()){
                                                    if(data.child("parantId").getValue().equals(uid)){ // to auth the user (p1 is a value in the database)
                                                        if(data.child("typeOfAllaregy").getValue().equals(productName)){// Egg(allargy) == Egg(barcode) you cant eat it
                                                            Log.d("you cant eat ",productName);
                                                            Log.d("","this product aint rigth for you :( ");
                                                            TextView textView = (TextView)findViewById(R.id.message);
                                                            textView.setTextColor(Color.WHITE);
                                                            textView.setText("طعام غير مناسب");
                                                            textView.setBackgroundColor(Color.parseColor("#ec4646"));
                                                            //textView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_thumb_down_24,0);
                                                            //textView.setdraw
                                                            //builder.setMessage("this product aint rigth for you :( ");
                                                        }else {
                                                            Log.d("you can eat ",productName);
                                                            Log.d("","You can eat this product : )");
                                                            TextView textView = (TextView)findViewById(R.id.message);
                                                            textView.setTextColor(Color.WHITE);
                                                            textView.setText("طعام مناسب");
                                                            textView.setBackgroundColor(Color.parseColor("#16c79a"));
                                                            //textView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_baseline_thumb_up_24,0);
                                                        }

                                                    }// end if
                                                }// end for loop
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                Log.w("value", "Failed to read child_info table. ", databaseError.toException());
                                            }
                                        }); // end child_info reference

                                    }else {
                                        TextView textView = (TextView)findViewById(R.id.message);
                                        textView.setTextColor(Color.WHITE);
                                        textView.setBackgroundColor(Color.parseColor("#ffcc29"));
                                        textView.setText("لم نتمكن من التعرف على المنتج، جرب منتج آخر!");

                                        //Log.d("","the given barcode aint exit in our database"); // barcode not in the database
                                    }
                                } // end for loop
                            }// end query Foods table

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.w("value", "Failed to read Foods table. ", databaseError.toException());
                            }
                        });// end reference Foods table

                    }else{
                        Toast.makeText(this,"No Result",Toast.LENGTH_LONG).show();
                    }
                }else {
                    super.onActivityResult(reqCode,resCode,data);
                }
            }catch (Exception e){
                Log.d("Exc ",e.toString());
            }
        }else {// No user is signed in
            Log.d("Exc ","you must sign in");
        }
    }



}