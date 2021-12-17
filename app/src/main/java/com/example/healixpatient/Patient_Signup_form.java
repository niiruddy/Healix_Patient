package com.example.healixpatient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Patient_Signup_form extends AppCompatActivity {
    FirebaseAuth fAuth;
    FirebaseDatabase fDB;
    DatabaseReference root;
    AutoCompleteTextView Gender;
    TextInputEditText Name;
    TextInputEditText Age;
    TextInputEditText Phone;
    TextInputEditText Height;
    TextInputEditText Weight;
    TextInputEditText Meds;
    TextInputEditText MedCon;
    TextInputEditText Allergy;
    Button Submit;
    ArrayList<String> arrayList_gender;
    ArrayAdapter<String> arrayAdapter_gender;
    long sendid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup_form);


        fAuth=FirebaseAuth.getInstance();
        fDB= FirebaseDatabase.getInstance();
        root= fDB.getReference().child("Patients");

        Gender=findViewById(R.id.p_gender);
        Phone=findViewById(R.id.pNum);
        Name=findViewById(R.id.p_name);
        Age=findViewById(R.id.Age);
        Submit=findViewById(R.id.p_submit);
        Height=findViewById(R.id.p_height);
        Weight=findViewById(R.id.p_weight);
        Meds=findViewById(R.id.meds);
        MedCon=findViewById(R.id.medcon);
        Allergy=findViewById(R.id.P_allerg);



        arrayList_gender=new ArrayList<>();
        arrayList_gender.add("Male");
        arrayList_gender.add("Female");
        arrayAdapter_gender=new ArrayAdapter<>(getApplicationContext(),R.layout.tv_entity,arrayList_gender);
        Gender.setAdapter(arrayAdapter_gender);
        Gender.setThreshold(1);



        Submit.setOnClickListener(view -> {
            final String FullName= Objects.requireNonNull(Name.getText()).toString();
            final String age=Age.getText().toString();
            final String phone_num=Phone.getText().toString();
            final String gender=Gender.getText().toString();
            final String height=Height.getText().toString();
            final String weight=Weight.getText().toString();
            final String MEDCON=MedCon.getText().toString();
            final String MEDS=Meds.getText().toString();
            final String all=Allergy.getText().toString();


            if (FullName.isEmpty()){
                Name.setError("Name Required");



            }else if(age.isEmpty()){
                Age.setError("Age Required");

            }

            else if(phone_num.isEmpty()){
                Phone.setError("Phone Required");

            }else if (gender.isEmpty()){
                Gender.setError("Gender required");
            }
            else if (height.isEmpty()){
                Height.setError("Height required");
            }
            else if (weight.isEmpty()){
                Weight.setError("Weight required");
            }

            else if (MEDCON.isEmpty()){
                MedCon.setError("Weight required");
            }
            else if (MEDS.isEmpty()){
                Meds.setError("Medications required");
            }

            else if (all.isEmpty()){
                Allergy.setError("Weight required");
            }
            else{

                SignInUser(FullName,age, phone_num,gender,height,weight,MEDCON,MEDS,all);
            }




    });





        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> {
            Intent intent=new Intent(Patient_Signup_form.this, patSignup.class);
            startActivity(intent);
            finish();
        });




    }

    private void SignInUser(String fullName,String age, String phone_num, String gender, String height, String weight, String allergy, String medcon, String meds) {


        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://app-healix-default-rtdb.firebaseio.com").child("Patients").child("Profile_details");

        /*FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String email = getIntent().getStringExtra("email");
        final String pass = getIntent().getStringExtra("pass");
        final String conPass = getIntent().getStringExtra("conPass");*/


       /* mAuth.createUserWithEmailAndPassword(email, conPass).addOnCompleteListener(Patient_Signup_form.this, task ->{
           if(task.isSuccessful()){
               Toast.makeText(Patient_Signup_form.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
               DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://app-healix-default-rtdb.firebaseio.com").child("Patients").child("Profile_details");
               ref.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if (snapshot.exists()){
                           sendid =(snapshot.getChildrenCount());
                       }

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
*/
               patient_register_helper helper = new patient_register_helper(fullName, age,  phone_num, gender, height, weight, allergy, meds,  medcon);
               ref.child(String.valueOf(sendid + 1)).setValue(helper);

               Intent intent = new Intent(Patient_Signup_form.this,Patient_Dashboard.class);

               // to prevent the user from using the back button to get to this page
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
               finish();


           }/*else{
               Toast.makeText(Patient_Signup_form.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
               Name.requestFocus();
           }

           }


        );*/

    }
