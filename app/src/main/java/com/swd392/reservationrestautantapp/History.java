package com.swd392.reservationrestautantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    // menu
    BottomNavigationView btv;

    // recycler view of history
    RecyclerView rcv;

    // list of history
    //List<History> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setupNavBottom();

    }

    // click on icon to start sreach
    /*private void setupIconClick() {
        //set up search icon click
        ImageView iconSearch = findViewById(R.id.iconSearch);
        iconSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterByName();
            }
        });
    }*/

    // sreach History by name or something
    /*private void filterByName() {
        EditText edValueSearch = findViewById(R.id.searchValue);
        String value = edValueSearch.getText().toString();
        List<Hisotry> listSearch = new ArrayList<>();
        for (History item: list) {
            if(item.get...().toLowerCase().contains(value.toLowerCase())){
                listSearch.add(item);
            }
        }

        Adapter historyAdapter = new Adapter(listSearch, this);
        rcv.setAdapter(historyAdapter);
    }*/

    // get list of history
    /*private List<History> getListHistory() {
        list = new ArrayList<>();
        ListDataSource listDataSource = new ListDataSource();
        list = listDataSource.getHistoryList();

        return list;
    }*/

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