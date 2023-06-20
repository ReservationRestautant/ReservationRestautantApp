package com.swd392.reservationrestautantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookingSuccess extends AppCompatActivity {

    // menu
    BottomNavigationView btv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);

        // button back to home page
        Button btnback = findViewById(R.id.buttonBack);

        // switch to home activity
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingSuccess.this, HomePage.class);
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
                    startActivity(new Intent(BookingSuccess.this, HomePage.class));
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