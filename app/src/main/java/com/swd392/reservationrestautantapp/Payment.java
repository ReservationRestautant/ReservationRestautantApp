package com.swd392.reservationrestautantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Payment extends AppCompatActivity {
    Button buttonDone;
    TextView moneypay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //find by id
        buttonDone = findViewById(R.id.buttonDone);
        moneypay = findViewById(R.id.moneypay);

        Intent intent = getIntent();
        String moneypaystr = intent.getStringExtra("MONEYPAY");
        //default data to TEST
        if(moneypaystr == null) moneypaystr = "...";

        //set money pay
        moneypay.setText("Money to pay: " + moneypaystr + " vnđ");

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Payment.this, BookingSuccess.class);
//                Intent intent1 = new Intent(Payment.this, BookingFail.class);
                startActivity(intent1);
            }
        });
    }
}