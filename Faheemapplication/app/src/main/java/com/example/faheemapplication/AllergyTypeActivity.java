package com.example.faheemapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AllergyTypeActivity extends AppCompatActivity {

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_type);
        getSupportActionBar().hide(); // Hide the action bar in the screen


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("child_info").child("typeOfAllaregy");
        AllergyType allergyType = new AllergyType();
        // When click on back button take the user back to the main screen
        ImageButton backButton = findViewById(R.id.backButton);
        Button saveButton = findViewById(R.id.actionButton);
        CheckBox checkbox1 = findViewById(R.id.checkbox1);
        CheckBox checkbox2 = findViewById(R.id.checkbox2);
        CheckBox checkbox3 = findViewById(R.id.checkbox3);
        CheckBox checkbox4 = findViewById(R.id.checkbox4);
        CheckBox checkbox5 = findViewById(R.id.checkbox5);
        CheckBox checkbox6 = findViewById(R.id.checkbox6);

        String v1 = "Milk";
        String v2 = "Soybean";
        String v3 = "Egg";
        String v4 = "Wheat";
        String v5 = "Nuts";
        String v6 = "Fish";
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AllergyTypeInfoSavedActivity(); // call mainActivity function so when user click the button the mainActivity is opened
                if (checkbox1.isChecked()){
                    allergyType.setAllergy1(v1);
                    reference.child(String.valueOf(i+1)).setValue(allergyType);
                }else {
                    //
                }
                if (checkbox2.isChecked()){
                    allergyType.setAllergy2(v2);
                    reference.child(String.valueOf(i+1)).setValue(allergyType);
                }else {
                    //
                }
                if (checkbox3.isChecked()){
                    allergyType.setAllergy3(v3);
                    reference.child(String.valueOf(i+1)).setValue(allergyType);
                }else {
                    //
                }
                if (checkbox4.isChecked()){
                    allergyType.setAllergy4(v4);
                    reference.child(String.valueOf(i+1)).setValue(allergyType);
                }else {
                    //
                }
                if (checkbox5.isChecked()){
                    allergyType.setAllergy5(v5);
                    reference.child(String.valueOf(i+1)).setValue(allergyType);
                }else {
                    //
                }
                if (checkbox6.isChecked()){
                    allergyType.setAllergy6(v6);
                    reference.child(String.valueOf(i+1)).setValue(allergyType);
                }else {
                    //
                }
            }
        });
    }
    public void mainActivity() {
        Intent intent = new Intent(this, mainScreen.class);
        startActivity(intent);
    }
    /* public void AllergyTypeInfoSavedActivity(){
        Intent intent = new Intent(this, AllergyTypeActivityInfoSaved.class);
        startActivity(intent);
    } */
}