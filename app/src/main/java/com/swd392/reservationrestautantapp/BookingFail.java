package com.swd392.reservationrestautantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookingFail extends AppCompatActivity {

    // menu
    BottomNavigationView btv;
    TextView textview3;

    private static final String PREFS_NAME = "MY_APP";

//    @Override
//    protected void onStop() {
//        super.onStop();
//        //xóa hết share reference
//        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.commit();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_fail);

//        setupNavBottom();

        // button back to home page
        Button btnback = findViewById(R.id.buttonBack);
        textview3 = findViewById(R.id.textview3);

        Intent intent = getIntent();
        textview3.setText(intent.getStringExtra("RESULT_FAIL"));

        // switch to home activity
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingFail.this, HomePage.class);
                startActivity(intent);
            }
        });

    }

//    private void setupNavBottom() {
//        btv = findViewById(R.id.bottom_nav);
//        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if(item.getItemId() == R.id.ac_home){
//                    System.out.println("btv_home_page");
//                    startActivity(new Intent(BookingFail.this, HomePage.class));
//                } else if(item.getItemId() == R.id.ac_history){
//                    //System.out.println("btv_ac_search_click");
//                    //startActivity(new Intent(HomePage.this, [...].class));
//                }else if(item.getItemId() == R.id.ac_user) {
//                    //System.out.println("btv_ac_favorite_click");
//                    //startActivity(new Intent(HomePage.this, [...].class));
//                }
//                return true;
//            }
//        });
//    }

}