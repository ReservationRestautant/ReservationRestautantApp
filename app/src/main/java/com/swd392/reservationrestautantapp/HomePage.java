package com.swd392.reservationrestautantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    // menu
    BottomNavigationView btv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        setupNavBottom();

        // Button booking
        Button btnbook = findViewById(R.id.buttonBooking);

        // switch to booking activity
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, Booking.class);
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
                    startActivity(new Intent(HomePage.this, HomePage.class));
                } else if(item.getItemId() == R.id.ac_history){
                    //System.out.println("btv_ac_search_click");
                    //startActivity(new Intent(HomePage.this, [...].class));
                }else if(item.getItemId() == R.id.ac_user) {
                    System.out.println("btv_ac_favorite_click");
                    startActivity(new Intent(HomePage.this, ProfileActivity.class));
                }
                return true;
            }
        });
    }

}