package com.example.faheemapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class checkProductActivity extends AppCompatActivity implements View.OnClickListener{

    private Button actionButton;
    Button scanBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_product);

        actionButton = (Button) findViewById(R.id.actionButton);
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

    public void mainActivity() {
        Intent intent = new Intent(this, mainScreen.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference foods = database.getReference("Foods"); //table foods
        DatabaseReference childInfo = database.getReference("child_info"); //table child_info
        try {
            IntentResult result = IntentIntegrator.parseActivityResult(reqCode,resCode,data);
            if(result != null){
                if(result.getContents() != null){// if it read Successfully (barcode)
                    foods.addValueEventListener(new ValueEventListener() { // a reference to Foods table
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) { // to query foods table
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) { //start for loop
                                if(snapshot.child("barcode").getValue().equals("199234")){ // 1234=result.grtConntnt(); يعني قراء الباركود وهو موجود في الداتابيس
                                    String  productName = snapshot.child("name").getValue().toString();// get the name of a given food barcode
                                    childInfo.addValueEventListener(new ValueEventListener() { // a reference to child_info table
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot1) { // to query child_info table
                                            for (DataSnapshot data : dataSnapshot1.getChildren()){
                                                if(data.child("parantId").getValue().equals("p1")){ // to auth the user (p1 is a value in the database)
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
                                    textView.setText("هذا المنتج غير موجود في الداتابيس لدينا");

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
    }
}