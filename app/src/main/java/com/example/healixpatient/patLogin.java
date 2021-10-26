package com.example.healixpatient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class patLogin extends AppCompatActivity {

    EditText email,password;
    Button submit;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_login);

        email=findViewById(R.id.loginEmail);
        password=findViewById(R.id.loginPassword);
        submit= findViewById(R.id.submitButton);
        mAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        TextView signUpPage=findViewById(R.id.signUpPage);

        signUpPage.setOnClickListener(view -> {

            Intent intent = new Intent(patLogin.this, patSignup.class);
            startActivity(intent);
            finish();
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em= email.getText().toString();
                String pass=password.getText().toString();

                if (em.isEmpty()){
                    email.setError("Email Required");



                }else if(pass.isEmpty()){
                    password.setError("Age Required");

                }

                else {
                    mAuth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(patLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Patient_Dashboard.class);
                                startActivity(intent);
                            }
                            else {
                                String error = task.getException().getMessage();

                                Toast.makeText(patLogin.this, error, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

            }
        }


    });
}
}