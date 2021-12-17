package com.example.healixpatient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class Patient_Dashboard extends AppCompatActivity {

    FloatingActionButton fab, fabChat;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    Boolean isOpen= false;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);
        fab=(FloatingActionButton) findViewById(R.id.fab);
        fabChat=(FloatingActionButton) findViewById(R.id.fab_chat);

        fabOpen= AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose=AnimationUtils.loadAnimation(this,R.anim.fab_close);
        rotateForward=AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackward=AnimationUtils.loadAnimation(this, R.anim.rotate_backward);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

            }

        });

        fabChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Toast.makeText(Patient_Dashboard.this, "Healix Care Chat", Toast.LENGTH_LONG).show();
            }
        });



        bottomNavigationView=findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment());
        bottomNavigationView.setSelectedItemId(R.id.pat_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()){

                    case R.id.pat_home:
                    fragment=new HomeFragment();
                    break;

                    case R.id.pat_request:
                        fragment=new RequestFragment();
                        break;

                    case R.id.pat_chat:
                        fragment=new ChatFragment();
                        break;

                    case R.id.pat_profile:
                        fragment=new ProfileFragment();
                        break;

                }
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();



                return true;
            }
        });

    }


    private void animateFab(){
        if(isOpen){
            fab.startAnimation(rotateForward);
            fabChat.startAnimation(fabClose);
            fabChat.setClickable(false);
            isOpen=false;
        }
        else {
            fab.startAnimation(rotateBackward);
            fabChat.startAnimation(fabOpen);
            fabChat.setClickable(true);
            isOpen=true;
        }

    }
}