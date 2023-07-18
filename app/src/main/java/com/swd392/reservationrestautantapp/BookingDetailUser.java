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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swd392.reservationrestautantapp.ApiService.ApiService;
import com.swd392.reservationrestautantapp.model.Reservation;
import com.swd392.reservationrestautantapp.model.ReservationHistory;
import com.swd392.reservationrestautantapp.model.ResponseObject;
import com.swd392.reservationrestautantapp.model.UserSystem;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetailUser extends AppCompatActivity {
    TextView tvphoneUser, tvnameUser, tvPeople, tvdate, tvslot, tvnote, tvprice;
    Button btnBack;
    private static final String PREFS_NAME = "MY_APP";
    BottomNavigationView btv;
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
        setContentView(R.layout.activity_booking_detail_user);

        SharedPreferences sharedPreferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);

        findbyIdEle();
        setupNavBottom();
        Intent intent = getIntent();
        //get reservation from api
        //test fake data
        ApiService.apiService.getdetailReservationById(sharedPreferences.getString("TOKEN", "") , intent.getIntExtra("ID_BOOKING", 0)).enqueue(new Callback<ResponseObject<ReservationHistory>>() {
            @Override
            public void onResponse(Call<ResponseObject<ReservationHistory>> call, Response<ResponseObject<ReservationHistory>> response) {
                if(response.isSuccessful()){
                    ReservationHistory reservation = response.body().getData();
                    if(reservation != null){
                        //set data to screen
                        tvphoneUser.setText(reservation.getUserSysterm().getPhone());
                        tvnameUser.setText(reservation.getUserSysterm().getName());
                        tvPeople.setText(reservation.getNumber_guest() + "");
                        tvdate.setText(reservation.getDate().toString());
                        tvslot.setText(reservation.getStartTime() + " - " + reservation.getEndTime());
                        tvprice.setText(reservation.getPrice() + " vnd");
                        tvnote.setText(reservation.getDescription());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<ReservationHistory>> call, Throwable t) {

            }
        });
//        reservation.setStartTime(new Time(7, 0, 0));
//        reservation.setEndTime(new Time(10, 0, 0));
//
//        long millis = System.currentTimeMillis();
//        Date date = new Date(millis);
//        reservation.setDate(date);
//
//        reservation.setNumber_guest(8);
//        reservation.setDescription("i am late about 15 minutes");
//        reservation.setStatus(true);
//        reservation.setPrice(1500000);
//        reservation.setDiscount(0);
//        reservation.setFeedback("");
//
//        UserSystem userSystem = new UserSystem();
//        userSystem.setName("Kha PHP");
//        userSystem.setPhone("0984736526");
//        reservation.setUserSysterm(userSystem);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingDetailUser.this, History.class));
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

    private void setupNavBottom() {
        btv = findViewById(R.id.bottom_nav);
        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.ac_home){
                    System.out.println("btv_home_page");
                    startActivity(new Intent(BookingDetailUser.this, HomePage.class));
                } else if(item.getItemId() == R.id.ac_history){
                    SharedPreferences sharedPreferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                    String guest_login = sharedPreferences.getString("GUEST", "");
                    if(guest_login.equals("true")){
                        Toast.makeText(BookingDetailUser.this, "Not Allow", Toast.LENGTH_SHORT).show();
                    }else {
                        startActivity(new Intent(BookingDetailUser.this, History.class));
                    }
                }else if(item.getItemId() == R.id.ac_user) {
                    startActivity(new Intent(BookingDetailUser.this, ProfileActivity.class));
                }
                return true;
            }
        });
    }
}