package com.example.healixpatient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class patSignup extends AppCompatActivity {

    EditText email, pass, conPass;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    TextView SignIn;
    Button next;
    String emailPattern = "[A-Za-z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        next =findViewById(R.id.next);
        email=findViewById(R.id.signupEmail);
        pass=findViewById(R.id.signupPassword);
        conPass=findViewById(R.id.signupConfirmPassword);
        SignIn=findViewById(R.id.signinPage);
        progressDialog = new ProgressDialog(this);

        Toolbar toolbar = findViewById(R.id.signupToolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onBackPressed();
                Intent intent=new Intent(patSignup.this, patLogin.class);
                startActivity(intent);
                finish();
            }
        });


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),patLogin.class));
                finish();
            }
        });





        next.setOnClickListener(view -> {
            String Email=email.getText().toString();
            String Pass=pass.getText().toString();
            String ConPass=conPass.getText().toString();



            if(!Email.matches(emailPattern)){
                email.setError("Enter Correct Email");
            }else if (Pass.isEmpty() || Pass.length()<6){
                pass.setError("Enter Proper Password");
            }else if(!Pass.equals(ConPass)) {
                conPass.setError("Password Not Match");
            }

            else{
                progressDialog.setMessage("Please Wait For Registration To Complete..... ");
                progressDialog.setTitle("Registering");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                





                mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            nextActivity();
                            Toast.makeText(patSignup.this, "Registration Complete", Toast.LENGTH_LONG).show();

                        }

                        else{

                            progressDialog.dismiss();
                            Toast.makeText(patSignup.this, ""+task.getException(),Toast.LENGTH_LONG).show();
                        }

                    }
                });


               /* intent.putExtra("email", Email);
        intent.putExtra("pass", Pass);
        intent.putExtra("conPass", ConPass);*/


            }


        });
    }

    private void nextActivity() {

        Intent intent = new Intent(patSignup.this, Patient_Signup_form.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }

}