package com.example.faheemapplication.CheckProduct.AllergyType;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.faheemapplication.HomeScreen.mainScreen;
import com.example.faheemapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AllergyTypeActivity extends AppCompatActivity {
    ChildInfo info;
    String id;
    DatabaseReference Reference;
    Button saveButton;
    ImageButton backButton;
    AllergyType typeOfAllergy;
   // RadioButton RadioButton1,RadioButton2,RadioButton3,RadioButton4;
    RadioGroup RadioGrp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_type);
        getSupportActionBar().hide(); // Hide the action bar in the screen


        Reference = FirebaseDatabase.getInstance().getReference("child_info");
        RadioGrp = findViewById(R.id.RadioGroups);
        saveButton = findViewById(R.id.actionButton);
       /* RadioButton1 = findViewById(R.id.RadioButton1); // Milk
        RadioButton2 = findViewById(R.id.RadioButton2); // Wheat
        RadioButton3 = findViewById(R.id.RadioButton3); // Egg
        RadioButton4 = findViewById(R.id.RadioButton4); // Nuts

        */


       // typeOfAllergy = new AllergyType();
       // id = Reference.push().getKey(); // Generate unique key for the user

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildInfo();
            }
                /* if (RadioButton1.isChecked()){
                    typeOfAllergy.setAllergy1("Milk");
                    Reference.child(id).setValue(typeOfAllergy);
                }
                if (RadioButton2.isChecked()){
                    typeOfAllergy.setAllergy2("Wheat");
                    Reference.child(id).setValue(typeOfAllergy);
                }
                if (RadioButton3.isChecked()){
                    typeOfAllergy.setAllergy3("Egg");
                    Reference.child(id).setValue(typeOfAllergy);
                }
                if (RadioButton4.isChecked()){
                    typeOfAllergy.setAllergy4("Nuts");
                    Reference.child(id).setValue(typeOfAllergy);
                }

               // info = new ChildInfo(id, typeOfAllergy);
                Reference.child(id).setValue(id);


                Intent intent = new Intent(AllergyTypeActivity.this , AllergyTypeActivityInfoSaved.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity(); // call mainActivity function so when user click the button the mainActivity is opened
            } */
        });
    }
    private void ChildInfo(){
        String Allergies = ((RadioButton)findViewById(RadioGrp.getCheckedRadioButtonId())).getText().toString();

        if(!TextUtils.isEmpty(Allergies)){
            id = Reference.push().getKey(); // Generate unique key for the user
            info = new ChildInfo(id, Allergies); // for each user add unique id for them along with the selected allergy type
            Reference.child(id).setValue(info);

        }else{
            Toast.makeText(this, "الرجاء اختيار احد الخيارات!" , Toast.LENGTH_LONG).show();
        }
    }

    public void mainActivity(){
        Intent intent = new Intent(this, mainScreen.class);
        startActivity(intent);
    }
}
