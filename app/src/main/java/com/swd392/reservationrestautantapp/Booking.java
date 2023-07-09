package com.swd392.reservationrestautantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

public class Booking extends AppCompatActivity {

    // menu
    BottomNavigationView btv;
    boolean cbtn710,cbtn1013,cbtn1316,cbtn1619,cbtn1922;
    Button btn710,btn1013,btn1316,btn1619,btn1922;
    String time;
    int monthChose;
    java.sql.Date datebooking;
    CalendarView calendarView;
    EditText numbergest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        setupNavBottom();
        datebooking = null;
        monthChose = 0;
        // num of guest
        EditText num_guest = findViewById(R.id.numbergest);

        // button choose time to reservation
        btn710 = findViewById(R.id.buttonBooking710);
        btn1013 = findViewById(R.id.buttonBooking1013);
        btn1316 = findViewById(R.id.buttonBooking1316);
        btn1619 = findViewById(R.id.buttonBooking1619);
        btn1922 = findViewById(R.id.buttonBooking1922);

        calendarView = findViewById(R.id.calendarView);

        numbergest = findViewById(R.id.numbergest);

        //check click button
        cbtn710 = false;
        cbtn1013 = false;
        cbtn1316 = false;
        cbtn1619 = false;
        cbtn1922 = false;

        //limit date chose for booking
        Calendar calendar = Calendar.getInstance();
            //set date min
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        Date DATEMIN = calendar.getTime();
        calendarView.setMinDate(DATEMIN.getTime());

            //set date max
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 3);
        Date DATEMAX = calendar.getTime();
        calendarView.setMaxDate(DATEMAX.getTime());
        //end limit date chose for booking

        //get date from calendar click
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String datestr = year + "-" + (month + 1) + "-" + dayOfMonth;
                datebooking = java.sql.Date.valueOf(datestr);
                monthChose = month + 1;
//                Log.d("BOOKING_INFO", "Ngày được chọn: " + datebooking.toString());
            }
        });

        // button booking
        Button btnbook = findViewById(R.id.buttonBooking);

        // switch to booking activity 2
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                boolean check = true;
                //number guest valid
                int guest = 0;
                try{
                    guest = Integer.parseInt(numbergest.getText().toString());
                    if(guest <= 0) throw new Exception();
                    if(guest > 24) throw new Exception();
                }catch (Exception ex){
                    check = false;
                    Toast.makeText(Booking.this, "Number guest must number bigger 0 and not more 24 peoples", Toast.LENGTH_SHORT).show();
                }
                Log.e("BOOKING_INFO", "nunber guesst: " + guest);
                editor.putString("BOOKING_INFO_NUMBER_GUEST", guest + "");
                editor.commit();

                if(time == null){   //check time
                    check = false;
                    Toast.makeText(Booking.this, "Please chose time", Toast.LENGTH_SHORT).show();
                }
                Log.e("BOOKING_INFO", "time: " + time);
                editor.putString("BOOKING_INFO_TIME", time);
                editor.commit();

                //date valid
                if(datebooking == null){    //check date
//                    check = false;
                    datebooking = new java.sql.Date(calendarView.getDate());
//                    Toast.makeText(Booking.this, "Please chose date", Toast.LENGTH_SHORT).show();
                }
                Log.e("BOOKING_INFO", "date: "+datebooking.toString());
                int month3 = (Calendar.getInstance().get(Calendar.MONTH) + 4);
//                Log.e("BOOKING_INFO", "month3: " + month3);
                if(monthChose == 0) monthChose = Calendar.getInstance().get(Calendar.MONTH) + 1;
                if(monthChose  > month3){
                    check = false;
                    Toast.makeText(Booking.this, "Can't booking date more than three month next", Toast.LENGTH_SHORT).show();
                }
                editor.putString("BOOKING_INFO_DATE", datebooking.toString());
                editor.commit();

                if(check){
                    //Toast.makeText(Booking.this, "Time: " + time, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Booking.this, Booking2.class);
                    startActivity(intent);
                }

            }
        });

        btn710.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbtn710 == false){
                    //click
                    time = "710";
                    btn710.setBackgroundColor(Color.parseColor("#32B768"));
                    btn710.setTextColor(Color.parseColor("#FFFFFF"));
                    cbtn710 = true;
                    disOrtherClickBUtton("710");
                }else{
                    //unclick
                    time = null;
                    btn710.setBackgroundColor(Color.parseColor("#D1FFE4"));
                    btn710.setTextColor(Color.parseColor("#000000"));
                    cbtn710 = false;
                }
            }
        });

        btn1013.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbtn1013 == false){
                    //click
                    time = "1013";
                    btn1013.setBackgroundColor(Color.parseColor("#32B768"));
                    btn1013.setTextColor(Color.parseColor("#FFFFFF"));
                    cbtn1013 = true;
                    disOrtherClickBUtton("1013");
                }else{
                    //unclick
                    time = null;
                    btn1013.setBackgroundColor(Color.parseColor("#D1FFE4"));
                    btn1013.setTextColor(Color.parseColor("#000000"));
                    cbtn1013 = false;
                }
            }
        });

        btn1316.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbtn1316 == false){
                    //click
                    time = "1316";
                    btn1316.setBackgroundColor(Color.parseColor("#32B768"));
                    btn1316.setTextColor(Color.parseColor("#FFFFFF"));
                    cbtn1316 = true;
                    disOrtherClickBUtton("1316");
                }else{
                    //unclick
                    time = null;
                    btn1316.setBackgroundColor(Color.parseColor("#D1FFE4"));
                    btn1316.setTextColor(Color.parseColor("#000000"));
                    cbtn1316 = false;
                }
            }
        });

        btn1619.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbtn1619 == false){
                    //click
                    time = "1619";
                    btn1619.setBackgroundColor(Color.parseColor("#32B768"));
                    btn1619.setTextColor(Color.parseColor("#FFFFFF"));
                    cbtn1619 = true;
                    disOrtherClickBUtton("1619");
                }else{
                    //unclick
                    time = null;
                    btn1619.setBackgroundColor(Color.parseColor("#D1FFE4"));
                    btn1619.setTextColor(Color.parseColor("#000000"));
                    cbtn1619 = false;
                }
            }
        });

        btn1922.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbtn1922 == false){
                    //click
                    time = "1922";
                    btn1922.setBackgroundColor(Color.parseColor("#32B768"));
                    btn1922.setTextColor(Color.parseColor("#FFFFFF"));
                    cbtn1922 = true;
                    disOrtherClickBUtton("1922");
                }else{
                    //unclick
                    time = null;
                    btn1922.setBackgroundColor(Color.parseColor("#D1FFE4"));
                    btn1922.setTextColor(Color.parseColor("#000000"));
                    cbtn1922 = false;
                }
            }
        });
    }

    private void disOrtherClickBUtton(String s) {
        if(!s.equals("710")){
            btn710.setBackgroundColor(Color.parseColor("#D1FFE4"));
            btn710.setTextColor(Color.parseColor("#000000"));
            cbtn710 = false;
        }
        if(!s.equals("1013")){
            btn1013.setBackgroundColor(Color.parseColor("#D1FFE4"));
            btn1013.setTextColor(Color.parseColor("#000000"));
            cbtn1013 = false;
        }
        if(!s.equals("1316")){
            btn1316.setBackgroundColor(Color.parseColor("#D1FFE4"));
            btn1316.setTextColor(Color.parseColor("#000000"));
            cbtn1316 = false;
        }
        if(!s.equals("1619")){
            btn1619.setBackgroundColor(Color.parseColor("#D1FFE4"));
            btn1619.setTextColor(Color.parseColor("#000000"));
            cbtn1619 = false;
        }
        if(!s.equals("1922")){
            btn1922.setBackgroundColor(Color.parseColor("#D1FFE4"));
            btn1922.setTextColor(Color.parseColor("#000000"));
            cbtn1922 = false;
        }
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