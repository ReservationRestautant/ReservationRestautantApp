package com.swd392.reservationrestautantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swd392.reservationrestautantapp.model.ReservationDTO;

import java.sql.Date;
import java.sql.Time;

public class Booking2 extends AppCompatActivity {

    // menu
    BottomNavigationView btv;
    float priceNeed;
    Time start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking2);

        setupNavBottom();

        // number guest pay for table
        EditText numdeposit = findViewById(R.id.numdeposit);
        numdeposit.setEnabled(false);

        // button for booking
        Button btnbooking = findViewById(R.id.buttonBooking);

        // user booking note
        EditText guestnote = findViewById(R.id.usernote);

        //get guest to caculate deposit
        SharedPreferences sharedPreferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String numbergueststr = sharedPreferences.getString("BOOKING_INFO_NUMBER_GUEST", "");
        int numberguest = Integer.parseInt(numbergueststr);
        int numbertableNeed = numberguest / 6;    //aly61 số bàn để full ng
        if(numberguest % 6 != 0){         //nếu dư ng thì cứ tính thếm bàn nưa
            numbertableNeed++;
        }
        priceNeed = numbertableNeed * 750000;
        numdeposit.setText(priceNeed + " vnđ");

        // switch to booking success or fail
        btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("BOOKING_INFO", "price: " + priceNeed);
                Log.e("BOOKING_INFO", "note: " + guestnote.getText().toString());

                String numbergueststr = sharedPreferences.getString("BOOKING_INFO_NUMBER_GUEST", "");
                String time = sharedPreferences.getString("BOOKING_INFO_TIME", "");
                String date = sharedPreferences.getString("BOOKING_INFO_DATE", "");
                String phonecus = sharedPreferences.getString("BOOKING_INFO_PHONE_CUS", "");
                String phoneguest = sharedPreferences.getString("BOOKING_INFO_PHONE_GUEST", "");
                String desciption = guestnote.getText().toString();

                //test fake data phone
                phonecus = "0000000000";
//                phonecus = "0971724708";
                phoneguest = "0971724708";
                //end test fake data phone

                try {
                    getTimeBooking(time);
                }catch (Exception ex){
                    Toast.makeText(Booking2.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ReservationDTO reservationDTO = new ReservationDTO();
                //set data reservation
                reservationDTO.setDescription(desciption);
                reservationDTO.setDate(Date.valueOf(date));
                reservationDTO.setStartTime(start);
                reservationDTO.setEndTime(end);
                reservationDTO.setNumber_guest(Integer.parseInt(numbergueststr));
                //set phone
                if(phonecus.equals("0000000000")){
                    //guest booking
                    reservationDTO.setPhone_cus(phonecus);
                    reservationDTO.setPhone_guest(phoneguest);
                }else{
                    //cus booking
                    reservationDTO.setPhone_cus(phonecus);
                }
                Log.e("BOOKING_INFO", "reservationDTO: " + reservationDTO.toString());

                //call api ở đây

                Intent intent = new Intent(Booking2.this, Payment.class);
                startActivity(intent);
            }
        });

    }

    private void getTimeBooking(String time) throws Exception {
        switch (time){
            case "710":
                start = new Time(7,0,0);
                end = new Time(10,0,0);
                break;
            case "1013":
                start = new Time(10,0,0);
                end = new Time(13,0,0);
                break;
            case "1316":
                start = new Time(13,0,0);
                end = new Time(16,0,0);
                break;
            case "1619":
                start = new Time(16,0,0);
                end = new Time(19,0,0);
                break;
            case "1922":
                start = new Time(19,0,0);
                end = new Time(22,0,0);
                break;
            default:
                throw new Exception("can get time booking");
        }
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
                    System.out.println("btv_ac_search_click");
                    startActivity(new Intent(Booking2.this, History.class));
                }else if(item.getItemId() == R.id.ac_user) {
                    System.out.println("btv_ac_favorite_click");
                    startActivity(new Intent(Booking2.this, ProfileActivity.class));
                }
                return true;
            }
        });
    }

}