package com.example.healixpatient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {
    TextView pat_headName;

    TextInputEditText pat_name, pat_age, pat_gender, pat_phone, pat_height, pat_weight, pat_medcon, pat_meds, pat_allergies;
    FirebaseAuth fAuth;
    DatabaseReference dbRef;
    String ID;
    private patient_register_helper phelper;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* fAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        assert currentUser != null;
        ID = currentUser.getEmail();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        pat_name = view.findViewById(R.id.Pat_Name);
        pat_age= view.findViewById(R.id.Pat_Age);
        pat_phone = view.findViewById(R.id.Pat_Phone);
        pat_gender = view.findViewById(R.id.Pat_Gender);
        pat_height = view.findViewById(R.id.Pat_Height);
        pat_weight = view.findViewById(R.id.Pat_Weight);
        pat_medcon=view.findViewById(R.id.Pat_MedCon);
        pat_meds=view.findViewById(R.id.Pat_Meds);
        pat_allergies=view.findViewById(R.id.Pat_Allergies);
        pat_headName = view.findViewById(R.id.Pat_headName);

        Query query = FirebaseDatabase.getInstance().getReference().child("Patients")
                .child("Profile_details").equalTo(ID);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ps : snapshot.getChildren()) {
                    phelper = ps.getValue(patient_register_helper.class);

                }

                pat_name.setText(phelper.getFullName());
                pat_age.setText(phelper.getAge());
                pat_gender.setText(phelper.getGender());
                pat_phone.setText(phelper.getPhone_num());
                pat_height.setText(phelper.getHeight());
                pat_weight.setText(phelper.getWeight());
                pat_medcon.setText(phelper.getMedcon());
                pat_meds.setText(phelper.getMeds());
                pat_allergies.setText(phelper.getAllergy());
                pat_headName.setText(phelper.getFullName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });
        return view;
    }
}
