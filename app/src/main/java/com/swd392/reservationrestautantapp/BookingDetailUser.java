package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.swd392.reservationrestautantapp.model.Reservation;
import com.swd392.reservationrestautantapp.model.UserSystem;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class BookingDetailUser extends AppCompatActivity {
    TextView tvphoneUser, tvnameUser, tvPeople, tvdate, tvslot, tvnote, tvprice;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail_user);
        
        findbyIdEle();

        //get reservation from api
        //test fake data
        Reservation reservation =new Reservation();
        reservation.setStartTime(new Time(7, 0, 0));
        reservation.setEndTime(new Time(10, 0, 0));

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        reservation.setDate(date);

        reservation.setNumber_guest(8);
        reservation.setDescription("i am late about 15 minutes");
        reservation.setStatus(true);
        reservation.setPrice(1500000);
        reservation.setDiscount(0);
        reservation.setFeedback("");

        UserSystem userSystem = new UserSystem();
        userSystem.setName("Kha PHP");
        userSystem.setPhone("0984736526");
        reservation.setUserSysterm(userSystem);

        //set data to screen
        tvphoneUser.setText(reservation.getUserSysterm().getPhone());
        tvnameUser.setText(reservation.getUserSysterm().getName());
        tvPeople.setText(reservation.getNumber_guest() + "");
        tvdate.setText(reservation.getDate().toString());
        tvslot.setText(reservation.getStartTime() + " - " + reservation.getEndTime());
        tvprice.setText(reservation.getPrice() + " vnd");
        tvnote.setText(reservation.getDescription());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(BookingDetailUser.this, [].class));
            }
        });

    }

    private void findbyIdEle() {
        tvphoneUser = findViewById(R.id.tvphoneUser);
        tvnameUser = findViewById(R.id.tvnameUser);
        tvPeople = findViewById(R.id.tvPeople);
        tvdate = findViewById(R.id.tvdate);
        tvslot = findViewById(R.id.tvslot);
        tvnote = findViewById(R.id.tvnote);
        btnBack = findViewById(R.id.btnBack);
        tvprice = findViewById(R.id.tvprice);
    }
}