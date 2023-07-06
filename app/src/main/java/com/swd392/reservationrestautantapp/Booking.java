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

public class Booking extends AppCompatActivity {

    // menu
    BottomNavigationView btv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        setupNavBottom();

        // num of guest
        EditText num_guest = findViewById(R.id.numbergest);

        // button choose time to reservation
        Button btn710 = findViewById(R.id.buttonBooking710);
        Button btn1013 = findViewById(R.id.buttonBooking1013);
        Button btn1316 = findViewById(R.id.buttonBooking1316);
        Button btn1619 = findViewById(R.id.buttonBooking1619);
        Button btn1922 = findViewById(R.id.buttonBooking1922);

        // button booking
        Button btnbook = findViewById(R.id.buttonBooking);

        // switch to booking activity 2
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Booking.this, Booking2.class);
                startActivity(intent);
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
                    startActivity(new Intent(Booking.this, HomePage.class));
                } else if(item.getItemId() == R.id.ac_history){
//                    System.out.println("btv_ac_search_click");
//                    startActivity(new Intent(HomePage.this, [...].class));
                }else if(item.getItemId() == R.id.ac_user) {
                    System.out.println("btv_ac_favorite_click");
                    startActivity(new Intent(Booking.this, ProfileActivity.class));
                }
                return true;
            }
        });
    }

}