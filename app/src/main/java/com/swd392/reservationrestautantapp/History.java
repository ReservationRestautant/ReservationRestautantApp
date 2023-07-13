package com.swd392.reservationrestautantapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swd392.reservationrestautantapp.ApiService.ApiService;
import com.swd392.reservationrestautantapp.adapter.HistoryAdapter;
import com.swd392.reservationrestautantapp.model.Reservation;
import com.swd392.reservationrestautantapp.model.ReservationHistory;
import com.swd392.reservationrestautantapp.model.ResponseObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {

    // menu
    BottomNavigationView btv;

    // recycler view of history
    RecyclerView rcv;

    // list of reservation history
    List<Reservation> list;

    // Uid of current user by Shared Preferences
    int Uid = 3; /*getResources().getInteger(R.integer.[ Uid of current user ]);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // get current user id via sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);	//"MY_APP": chỉ là cái tên của Shared preference;
        //Uid = sharedPreferences.getInt("Key_User", 0);

        // set up bottom menu
        setupNavBottom();

        // call api to get list of reservation by id of current user
        //callApiGetListReservation(Uid);
        //call api
        List<ReservationHistory> list1 = new ArrayList<>();
        ApiService.apiService.getReservationById(Uid).enqueue(new Callback<ResponseObject<List<ReservationHistory>>>() {
            @Override
            public void onResponse(Call<ResponseObject<List<ReservationHistory>>> call, Response<ResponseObject<List<ReservationHistory>>> response) {
                for (ReservationHistory r: response.body().getData()) {
                    Log.e("HISTORY_ID_RESERVATION", String.valueOf(r.getId()));
                    Log.e("HISTORY_DATE", r.getDate().toString());
                    Log.e("HISTORY_NUMBER_GUEST", String.valueOf(r.getNumber_guest()));
                    Log.e("HISTORY_PRICE", String.valueOf(r.getPrice()));
                    //getStartTime getNumber_guest getPrice
                    ReservationHistory obj = new ReservationHistory();
                    obj.setStartTime(r.getStartTime());
                    obj.setNumber_guest(r.getNumber_guest());
                    obj.setPrice(r.getPrice());
                    obj.setId(r.getId());
                    list1.add(obj);
                }
                Log.e("TRACK", "list1 size: "+list1.size());
                Log.e("TRACK", "list1 size now: "+list1.size());
                HistoryAdapter historyadapter = new HistoryAdapter(list, History.this, list1);
                rcv.setAdapter(historyadapter);
            }
            @Override
            public void onFailure(Call<ResponseObject<List<ReservationHistory>>> call, Throwable t) {
                Log.e("ERROR", "call api fail " + t);
            }
        });
        //call api - end



        // setup recycler view
        rcv = findViewById(R.id.rcv_History);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);   //dạng cột và có 1 cột
        rcv.setLayoutManager(gridLayoutManager);


        //set up search icon click
        ImageView iconSearch = findViewById(R.id.iconSearch);
        iconSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // some method
            }
        });

    }

//    private void callApiGetListReservation(int uid){
//        list = new ArrayList<>();
//        ApiService.apiService.getReservationById(uid).enqueue(new Callback<ResponseObject<List<Reservation>>>() {
//            @Override
//            public void onResponse(Call<ResponseObject<List<Reservation>>> call, Response<ResponseObject<List<Reservation>>> response) {
//                for (Reservation r: response.body().getData()) {
//                    Log.e("ID_RESERVATION", String.valueOf(r.getId()));
//                    Log.e("DATE", r.getDate().toString());
//                    Log.e("NUMBER_GUEST", String.valueOf(r.getNumber_guest()));
//                    Log.e("PRICE", String.valueOf(r.getPrice()));
//                    list.add(r);
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseObject<List<Reservation>>> call, Throwable t) {
//                Log.e("ERROR", "call api fail");
//            }
//        });
//    }

    private void setupNavBottom() {
        btv = findViewById(R.id.bottom_nav);
        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.ac_home){
                    System.out.println("btv_home_page");
                    startActivity(new Intent(History.this, HomePage.class));
                } else if(item.getItemId() == R.id.ac_history){
                    startActivity(new Intent(History.this, History.class));
                }else if(item.getItemId() == R.id.ac_user) {
                    startActivity(new Intent(History.this, ProfileActivity.class));
                }
                return true;
            }
        });
    }

}