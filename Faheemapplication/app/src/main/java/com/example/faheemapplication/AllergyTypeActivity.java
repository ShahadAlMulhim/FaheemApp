package com.example.faheemapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AllergyTypeActivity extends AppCompatActivity {
    ChildInfo info;
    String id;
    DatabaseReference Reference;
    Button saveButton;
    ImageButton backButton;
    AllergyType typeOfAllergy;
    CheckBox checkbox1,checkbox2,checkbox3,checkbox4,checkbox5,checkbox6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_type);
        getSupportActionBar().hide(); // Hide the action bar in the screen


        Reference = FirebaseDatabase.getInstance().getReference("child_info");
        checkbox1 = findViewById(R.id.checkbox1); // Milk
        checkbox2 = findViewById(R.id.checkbox2); // Soybean
        checkbox3 = findViewById(R.id.checkbox3); // Egg
        checkbox4 = findViewById(R.id.checkbox4); // Wheat
        checkbox5 = findViewById(R.id.checkbox5); // Nuts
        checkbox6 = findViewById(R.id.checkbox6); // Fish


        typeOfAllergy = new AllergyType();
        id = Reference.push().getKey(); // Generate unique key for the user 

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            }
        });

        saveButton = findViewById(R.id.actionButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (checkbox1.isChecked()){
                   typeOfAllergy.setAllergy1("Milk");
                   Reference.child(id).child("typeOfallergy").setValue(typeOfAllergy);
                }
                if (checkbox2.isChecked()){
                    typeOfAllergy.setAllergy2("Soybean");
                    Reference.child(id).child("typeOfallergy").setValue(typeOfAllergy);
                }
                if (checkbox3.isChecked()){
                    typeOfAllergy.setAllergy3("Egg");
                    Reference.child(id).child("typeOfallergy").setValue(typeOfAllergy);
                }
                if (checkbox4.isChecked()){
                    typeOfAllergy.setAllergy4("Wheat");
                    Reference.child(id).child("typeOfallergy").setValue(typeOfAllergy);
                }
                if (checkbox5.isChecked()){
                    typeOfAllergy.setAllergy5("Nuts");
                    Reference.child(id).child("typeOfallergy").setValue(typeOfAllergy);
                }
                if (checkbox6.isChecked()){
                    typeOfAllergy.setAllergy6("Fish");
                    Reference.child(id).child("typeOfallergy").setValue(typeOfAllergy);
                }

 
                info = new ChildInfo(id, typeOfAllergy);
                Reference.child(id).setValue(info);


                Intent intent = new Intent(AllergyTypeActivity.this , AllergyTypeActivityInfoSaved.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    public void mainActivity(){
        Intent intent = new Intent(this, mainScreen.class);
        startActivity(intent);
    }
}
