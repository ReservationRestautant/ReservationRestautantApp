package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OnBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        // button next onboard activity 1
        Button btnob = findViewById(R.id.buttonOnBoard);

        // switch to OnBoard activity 2
        btnob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoard.this, OnBoard2.class);
                startActivity(intent);
            }
        });

    }
}