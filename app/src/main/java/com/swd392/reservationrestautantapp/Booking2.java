package com.swd392.reservationrestautantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Booking2 extends AppCompatActivity {

    // menu
    BottomNavigationView btv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking2);

        // number guest pay for table
        EditText numdeposit = findViewById(R.id.numdeposit);

        // button for booking
        Button btnbooking = findViewById(R.id.buttonBooking);

        // user booking note
        EditText guestnote = findViewById(R.id.usernote);

        // switch to booking success or fail
        btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Booking2.this, [...].class);
                //startActivity(intent);
            }
        });

    }

    private void setupNavBottom() {
        btv = findViewById(R.id.bottom_nav);
        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.ac_home){
                    System.out.println("btv_home_page");
                    startActivity(new Intent(Booking2.this, HomePage.class));
                } else if(item.getItemId() == R.id.ac_history){
                    //System.out.println("btv_ac_search_click");
                    //startActivity(new Intent(HomePage.this, [...].class));
                }else if(item.getItemId() == R.id.ac_user) {
                    //System.out.println("btv_ac_favorite_click");
                    //startActivity(new Intent(HomePage.this, [...].class));
                }
                return true;
            }
        });
    }

}