package com.example.faheemapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;


public class AllergyTypeActivity extends AppCompatActivity {
    ChildInfo info;
    ArrayList<String> checkedCheckboxes;
    String id;
    // String[] Allergies = {"Milk","Soybeans","Egg","Wheat","Nuts","Fish"};
    DatabaseReference Reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_type);
        getSupportActionBar().hide(); // Hide the action bar in the screen


        Reference = FirebaseDatabase.getInstance().getReference("child_info");
        ImageButton backButton = findViewById(R.id.backButton);
        Button saveButton = findViewById(R.id.actionButton);
        CheckBox checkbox1 = findViewById(R.id.checkbox1); // Milk
        CheckBox checkbox2 = findViewById(R.id.checkbox2); // Soybean
        CheckBox checkbox3 = findViewById(R.id.checkbox3); // Egg
        CheckBox checkbox4 = findViewById(R.id.checkbox4); // Wheat
        CheckBox checkbox5 = findViewById(R.id.checkbox5); // Nuts
        CheckBox checkbox6 = findViewById(R.id.checkbox6); // Fish


        id = Reference.push().getKey();
        info = new ChildInfo(id, checkedCheckboxes);

     /*   Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError warn.png) {

            }
        }); */


        if (checkbox1.isChecked()){
            checkedCheckboxes.add("Milk");
        }
        if (checkbox2.isChecked()){
            checkedCheckboxes.add("Soybean");
        }
        if (checkbox3.isChecked()){
            checkedCheckboxes.add("Egg");
        }
        if (checkbox4.isChecked()){
            checkedCheckboxes.add("Wheat");
        }
        if (checkbox5.isChecked()){
            checkedCheckboxes.add("Nuts");
        }
        if (checkbox6.isChecked()) {
            checkedCheckboxes.add("Fish");
        }

        // checkedCheckboxes.addAll(Arrays.asList(Allergies));


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reference.child(id).setValue(info);

                // Reference.child(String.valueOf(i+1)).setValue(allergyType);

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
